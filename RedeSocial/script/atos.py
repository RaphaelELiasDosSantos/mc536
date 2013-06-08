#autor: Amadeu Bonfante
#.py que ke sql, abre links do wikipedia, extrai nome e cidades de bandas e insere no banco novamente.

import mechanize
import _mysql

import MySQLdb as mdb
import sys
from bs4 import BeautifulSoup

from HTMLParser import HTMLParser

#funcao que troca um caracter de uma string por outro
def retira_char( string, char1, char2):
  res = ""
  for c in string:
    if c == char1:
      res += char2
    else:
      res += c
  return res

#conecta com banco de dados
con = mdb.connect('localhost', 'root', 
        '', 'aula3');

with con: 
    #cur.execute executa query
    cur = con.cursor()
    cur.execute("SELECT DISTINCT ato FROM curtiratos")

    links = cur.fetchall()
    
    base = "http://en.wikipedia.org/wiki/"

    paises = []
    #mechanize para abrir um browse com url do wikipedia
    br = mechanize.Browser()
    br.addheaders = [('User-agent', 'Mozilla/5.0')] # A white lie
    #SOMA = 0
    #para cada link retira nome do head do html da pagina e procura cidades
    for link in links:
      ok = False
      br.open(base+link[0])
      r = br.response()
      html = r.read()
      #try para caso de caracter invalid0, (url zuado)
      try:
	#abre url, pega string do titulo e retira parte da string " - Wikipedia...."
	soup = BeautifulSoup(html)
	nome = soup.head.title.string
	n = nome.find("Wikipedia")
	#nome da banda
	nome = nome[:n-3]
	#seleciona todas a tag td, para localizar o pais do ato
	cols = soup.findAll("td")
	#try para tarta bando, onde pais de origem esta em uma tag entitulada "Orign"
	ok = True
	try:
	  num = 0;
	  info = cols[num].parent
	  #laco para localizar tag onde esta o pais de origm de uma banda
	  while True:
	    if info.th != None:
	      if info.th.string == "Origin":
		break
	    num +=1
	    info = cols[num].parent
	  #cidade origem
	  #caso em que cidade nao eh um link
	  if info.td.string != None:
	    city = info.td.string
	  #caso em que cidade eh um link
	  elif info.td.findAll("a") != []:
	    city = ""
	    for ele in info.td.findAll('a'):
	      if ele.string != None:
		city += ele.string+" "
	  #cidade nao localizada
	  else:
	    city = ""
	#try para tarta bando, onde pais de origem esta em uma tag entitulada "Born"
	except IndexError:
	  num = 0;
	  info = cols[num].parent
	  #laco para localizar tag onde esta o pais de origm de um musica
	  while True:
	    if info.th != None:
	      if info.th.string == "Born":
		break
	    num +=1
	    info = cols[num].parent
	  city = ""
	  #neste caso a cidades eh um link, entao um interator de todas os link formam a cidade origem do musico
	  for ele in info.td.findAll('a'):
	    if ele.string != None:
	      city += ele.string+" "
      #caso nao consiga ler o url insere no codigo link do wikipedia no nome e uma string vazia na ciade
      except:
	#trata o caso do parse nao conseguir pegar a cidade e emite um erro de execucao, nome =  nome da Wikipedia e nao o link
	if ok == False:
	  nome = link[0]
	city = ""
      #retira caracteres invalidos
      nome = retira_char(nome, "'", "")
      nome = retira_char(nome, "_", " ")
      city = retira_char(city, "'", "")
      
      #print nome, city

      print "insert into atos_musicais (nome,pais) values ('"+nome.encode("UTF-8")+"','"+city.encode("UTF-8")+"');"
      #insere dados no banco de dados
      cur.execute("insert into atos_musicais (nome,pais) values ('"+nome.encode("UTF-8")+"','"+city.encode("UTF-8")+"');")
