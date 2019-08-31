#! /usr/bin/python

import cgi

import cgitb
cgitb.enable()

HTML_HEADER = 'Content-type: text/html\n\n'

Top_HTML = '''
<html>
<head>
<title>Digimon Pet Simulator</title>
<link href='style1.css' type="text/css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">
<style>
body {
    background-image: url("background.png");
    margin: 10;
} 

*{
    font-family: 'Oswald', sans-serif;
}

tr{
    font-size:24px;
    color:white;
}

header{
    font-family: 'Oswald', sans-serif;
    font-size:64px;
    color:white;
}    

option{
    font-family: 'Oswald', sans-serif;
    font-size:24px;
        color:white;
}    

option{
    font-family: 'Oswald', sans-serif;
    font-size:24px;
}

input{
    font-family: 'Oswald', sans-serif;
    font-size:24px;
}
h{
    color:white;
}
</style>
</head>
<body>
<form method="get" action="ec2.py">
'''

Bottom_HTML = '</table></center> <center> <input type="submit" value="GO!" name="pushit2"></center></form></body></html>'

Digimons = {'First':['Botamon','Koromon','Agumon','Greymon','MetalGreymon','WarGreymon'],'Second':['Punimon','Tsunomon','Gabumon','Garurumon','WereGarurumon','MetalGarurumon'], 'Third': ['Nyokimon','Yokomon','Biyomon','Birdramon','Garudamon','Hououmon'], 'Fourth':['Yuramon','Tanemon','Palmon','Togemon','Lillymon','Rosemon'], 'Fifth':['Pabumon','Motimon','Tentomon','Kabuterimon','MegaKabuterimon','HerculesKabuterimon'], 'Sixth': ['Pichimon','Bukamon','Gomamon','Ikkakumon','Zudomon','Vikemon'], 'Seventh':['Poyomon','Tokomon','Patamon','Angemon','MagnaAngemon','Seraphimon'], 'Eighth':['YukimiBotamon','Nyaromon','Salmon','Gatomon','Angewomon','Ophanimon']}

def getdata():
    global name
    global name1
    elements = cgi.FieldStorage()
    name = elements.getvalue('Digimon','Nothing')
    name1 = Digimons[name]
    data = open('digimon.txt','w')
    data.write((','.join(name1)) + ',10,10,10,10,100,0,0')
    data.close()
    data = open('digimon.txt','rU').read()
    data = data.split(',') 
    print HTML_HEADER + Top_HTML + '<center><h>' + data[0] + '</h></center><center><table><tr><td><img src="' + data[0] + '.jpg"></td></tr></table></center><br/><br/><center><table><tr><td><input type="radio" value="food" name="action">Food</td><td><input type="radio" value="clean" name="action">Clean</td><td><input type="radio" value="toy" name="action">Toy</td><td><input type="radio" value="bandage" name="action">Bandage</td><td><input type="radio" value="train" name="action">Train</td><td><input type="radio" value="battle" name="action">Battle</td>'
    print '</tr><tr><td>' + data[6] + '</td><td>' + data[7] + '</td><td>' + data[8] + '</td><td>' + data[9] + '</td><td>' + data[10] + '</td><td>' + data[11] + '</td></tr>' + Bottom_HTML

getdata()
    

