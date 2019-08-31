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
from random import randint

def getdata():
    global data
    global writer
    elements = cgi.FieldStorage()
    action = elements.getvalue('action','Nothing')
    data = open('digimon.txt','rU').read().split(',')
    enemy = randint(((int(data[8]))/5),((int(data[8]))*10))
    if int(data[6]) <= 0 or int(data[7]) <= 0 or int(data[8]) <= 0 or int(data[9]) <= 0:
        print HTML_HEADER + '<html><head> <title>Digimon Pet Simulator</title><script language="javascript" type="text/javascript">alert("You killed your pet you monster!")</script><link href="style1.css" type="text/css" rel="stylesheet"><link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet"><style>*{font-size:64px;}</style></head><body><center><a href="ec1.html"><button>Gimme another pet</button></a></center></body></html>'
    elif int(data[11]) == 1000:
        if int(data[12]) >= 5:
            print HTML_HEADER + '<html><head> <title>Digimon Pet Simulator</title><script language="javascript" type="text/javascript">alert("Your Digimon is now a full-fledged BEAST!")</script><link href="style1.css" type="text/css" rel="stylesheet"><link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet"><style>*{font-size:64px;}</style></head><body><center><a href="ec1.html"><button>Gimme another pet</button></a></center></body></html>'
        else:
            data[11] = str(0)
            data[12] = str(int(data[12]) + 1)
            data[10] = str(int(data[10]) * 10)
            writer = open('digimon.txt','w')
            writer.write((','.join(data)))
            writer.close()
            data = open('digimon.txt','rU').read().split(',')
            print HTML_HEADER + Top_HTML + '<center><h>' + data[int(data[12])] + '</h></center><center><table><tr><td><img src="' + data[int(data[12])] + '.jpg"></td></tr></table></center><br/><br/><center><table><tr><td><input type="radio" value="food" name="action">Food</td><td><input type="radio" value="clean" name="action">Clean</td><td><input type="radio" value="toy" name="action">Toy</td><td><input type="radio" value="bandage" name="action">Bandage</td><td><input type="radio" value="train" name="action">Train</td><td><input type="radio" value="battle" name="action">Battle</td>'
            print '</tr><tr><td>' + data[6] + '</td><td>' + data[7] + '</td><td>' + data[8] + '</td><td>' + data[9] + '</td><td>' + data[10] + '</td><td>' + data[11] + '</td></tr>' + Bottom_HTML

    else:
        if action == 'food':
            data[6] = str(int(data[6]) + randint(1,5))
        elif action == 'clean':
            data[7] = str(int(data[7]) + randint(1,5))
        elif action == 'toy':
            data[8] = str(int(data[8]) + randint(1,5))
        elif action == 'bandage':
            data[9] = str(int(data[9]) + randint(1,5))
        elif action == 'train':
            data[10] = str(int(data[10]) + randint(1,5))
            data[6] = str(int(data[6]) - randint(0,5))
            data[7] = str(int(data[7]) - randint(0,5))
            data[8] = str(int(data[8]) - randint(0,5))
            data[9] = str(int(data[9]) - randint(0,5))
        elif action == 'battle':
            if int(data[6]) + int(data[7]) + int(data[8]) + int(data[9]) + int(data[10]) + int(data[11]) < enemy:
                data[6] = str(int(data[6]) - randint(6,8))
                data[7] = str(int(data[7]) - randint(6,8))
                data[8] = str(int(data[8]) - randint(6,8))
                data[9] = str(int(data[9]) - randint(6,8))
            if int(data[6]) + int(data[7]) + int(data[8]) + int(data[9]) + int(data[10]) + int(data[11]) >= enemy:                      
                data[11] = str(int(data[11]) + 100)
                data[6] = str(int(data[6]) - randint(0,5))
                data[7] = str(int(data[7]) - randint(0,5))
                data[8] = str(int(data[8]) - randint(0,5))
                data[9] = str(int(data[9]) - randint(0,5))
        writer = open('digimon.txt','w')
        writer.write((','.join(data)))
        writer.close()
        data = open('digimon.txt','rU').read().split(',')
        print HTML_HEADER + Top_HTML + '<center><h>' + data[int(data[12])] + '</h></center><center><table><tr><td><img src="' + data[int(data[12])] + '.jpg"></td></tr></table></center><br/><br/><center><table><tr><td><input type="radio" value="food" name="action">Food</td><td><input type="radio" value="clean" name="action">Clean</td><td><input type="radio" value="toy" name="action">Toy</td><td><input type="radio" value="bandage" name="action">Bandage</td><td><input type="radio" value="train" name="action">Train</td><td><input type="radio" value="battle" name="action">Battle</td>'
        print '</tr><tr><td>' + data[6] + '</td><td>' + data[7] + '</td><td>' + data[8] + '</td><td>' + data[9] + '</td><td>' + data[10] + '</td><td>' + data[11] + '</td></tr>' + Bottom_HTML

getdata()
    
    
                       
