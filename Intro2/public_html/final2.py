#! /usr/bin/python

import cgi

import cgitb
cgitb.enable()

HTML_HEADER = 'Content-type: text/html\n\n'

Top_HTML = '''
<html>
<head>
<title>Pokemon Battle-Sim</title>
<link href="style1.css" type="text/css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">
<style>
*{
    color:white;
}
option {
    color:black;
}
input{
    color:black;
}
</style>
</head>
<body>
<form method="get" action="final3.py">
<center><header>The Battle!</header></center>
<center><table border="1"><tr><td>
'''

Bottom_HTML = '</table></center> <center> <input type="submit" value="GO!" name="pushit2"></center></form></body></html>' 

pokestats = {'Golem': 'Golem,Rock,None,363,318,358,208,188', 'Farfetchd': 'Farfetchd,Flying,None,307,228,208,214,218', 'Pinsir': 'Pinsir,Bug,None,333,348,298,208,268', 'Weezing': 'Weezing,Poison,None,333,278,338,268,218', 'Moltres': 'Moltres,Fire,None,383,298,278,348,278', 'Flareon': 'Flareon,Fire,None,333,358,218,318,228', 'Cloyster': 'Cloyster,Water,None,303,288,458,268,238', 'Dragonite': 'Dragonite,Dragon,None,385,366,288,298,258', 'Beedrill': 'Beedrill,Bug,None,333,258,178,188,248', 'Tauros': 'Tauros,Normal,None,353,298,288,238,318', 'Fearow': 'Fearow,Flying,None,333,278,228,220,298', 'Mr-mime': 'Mr-mime,Psychic,None,283,188,228,298,278', 'Vaporeon': 'Vaporeon,Water,None,463,228,218,318,228', 'Kabutops': 'Kabutops,Rock,None,323,328,308,238,258', 'Sandslash': 'Sandslash,Ground,None,353,298,318,208,228', 'Kangaskhan': 'Kangaskhan,Normal,None,413,288,258,178,278', 'Arbok': 'Arbok,Poison,None,323,268,236,228,258', 'Mewtwo': 'Mewtwo,Psychic,None,415,318,278,406,358', 'Lickitung': 'Lickitung,Normal,None,383,208,248,218,158', 'Muk': 'Muk,Poison,None,413,308,248,228,198', 'Poliwrath': 'Poliwrath,Water,None,383,268,288,238,238', 'Machamp': 'Machamp,Fighting,None,383,358,258,228,208', 'Marowak': 'Marowak,Ground,None,323,258,318,198,188', 'Butterfree': 'Butterfree,Bug,None,323,188,198,258,238', 'Electabuzz': 'Electabuzz,Electric,None,333,264,212,268,308', 'Wigglytuff': 'Wigglytuff,Normal,None,483,238,188,198,188', 'Charizard': 'Charizard,Fire,None,359,266,254,268,298', 'Porygon': 'Porygon,Normal,None,333,218,238,248,178', 'Tangela': 'Tangela,Grass,None,333,208,328,298,218', 'Jynx': 'Jynx,Ice,None,333,198,168,288,288', 'Hypno': 'Hypno,Psychic,None,373,244,238,328,232', 'Rhydon': 'Rhydon,Ground,None,413,358,338,188,178', 'Hitmonlee': 'Hitmonlee,Fighting,None,303,338,204,168,272', 'Exeggutor': 'Exeggutor,Grass,None,393,288,268,348,208', 'Dewgong': 'Dewgong,Water,None,383,238,258,288,238', 'Hitmonchan': 'Hitmonchan,Fighting,None,303,308,256,168,250', 'Pidgeot': 'Pidgeot,Flying,None,369,258,248,238,280', 'Nothing': '', 'Alakazam': 'Alakazam,Psychic,None,313,198,188,368,338', 'Seaking': 'Seaking,Water,None,363,282,228,258,234', 'Nidoqueen': 'Nidoqueen,Poison,None,383,262,272,248,250', 'Vileplume': 'Vileplume,Grass,None,353,258,268,298,198', 'Venomoth': 'Venomoth,Bug,None,343,228,218,278,278', 'Jolteon': 'Jolteon,Electric,None,333,228,218,318,358', 'Victreebel': 'Victreebel,Grass,None,363,308,228,298,238', 'Primeape': 'Primeape,Fighting,None,333,308,218,218,288', 'Ditto': 'Ditto,Normal,None,299,194,194,194,194', 'Aerodactyl': 'Aerodactyl,Rock,None,363,308,228,218,358', 'Chansey': 'Chansey,Normal,None,703,108,108,308,198', 'Starmie': 'Starmie,Water,None,323,248,268,298,328', 'Gyarados': 'Gyarados,Water,None,393,348,256,298,260', 'Tentacruel': 'Tentacruel,Water,None,363,238,228,338,298', 'Clefable': 'Clefable,Normal,None,393,238,244,268,218', 'Golbat': 'Golbat,Poison,None,353,258,238,248,278', 'Ninetales': 'Ninetales,Fire,None,349,250,248,298,298', 'Mew': 'Mew,Psychic,None,403,298,298,298,298', 'Raichu': 'Raichu,Electric,None,323,278,208,278,298', 'Onix': 'Onix,Rock,None,273,188,418,158,238', 'Rapidash': 'Rapidash,Fire,None,333,298,238,258,308', 'Raticate': 'Raticate,Normal,None,313,260,218,198,292', 'Magneton': 'Magneton,Electric,None,303,218,288,338,238', 'Golduck': 'Golduck,Water,None,363,262,254,258,268', 'Parasect': 'Parasect,Bug,None,323,288,258,258,158', 'Dugtrio': 'Dugtrio,Ground,None,273,258,198,238,338', 'Lapras': 'Lapras,Water,None,463,268,258,288,218', 'Scyther': 'Scyther,Bug,None,343,318,258,208,308', 'Dodrio': 'Dodrio,Normal,None,323,318,238,218,298', 'Slowbro': 'Slowbro,Water,None,393,248,318,258,158', 'Seadra': 'Seadra,Water,None,313,228,288,288,268', 'Arcanine': 'Arcanine,Fire,None,383,318,258,258,288', 'Omastar': 'Omastar,Rock,None,343,218,348,328,208', 'Persian': 'Persian,Normal,None,333,238,218,228,328', 'Gengar': 'Gengar,Ghost,None,323,228,218,358,318', 'Nidoking': 'Nidoking,Poison,None,365,282,252,248,268', 'Magmar': 'Magmar,Fire,None,333,288,212,268,284', 'Zapdos': 'Zapdos,Electric,None,383,278,268,348,298', 'Snorlax': 'Snorlax,Normal,None,523,318,228,228,158', 'Venusaur': 'Venusaur,Grass,None,363,262,264,298,258', 'Electrode': 'Electrode,Electric,None,323,198,238,258,378', 'Articuno': 'Articuno,Ice,None,383,268,298,348,268', 'Kingler': 'Kingler,Water,None,313,358,328,198,248', 'Blastoise': 'Blastoise,Water,None,361,264,298,268,254'} 
pokemontoid={'charmeleon': '5', 'krabby': '98', 'spearow': '21', 'arcanine': '59', 'venusaur': '3', 'charmander': '4', 'articuno': '144', 'pinsir': '127', 'weezing': '110', 'mewtwo': '150', 'cloyster': '91', 'dragonite': '149', 'tauros': '128', 'fearow': '22', 'paras': '46', 'kadabra': '64', 'jigglypuff': '39', 'sandslash': '28', 'abra': '63', 'hitmonlee': '106', 'lickitung': '108', 'machamp': '68', 'haunter': '93', 'wigglytuff': '40', 'kangaskhan': '115', 'machop': '66', 'goldeen': '118', 'gengar': '94', 'poliwhirl': '61', 'hitmonchan': '107', 'clefable': '36', 'ditto': '132', 'gloom': '44', 'tentacool': '72', 'primeape': '57', 'magnemite': '81', 'squirtle': '7', 'vulpix': '37', 'koffing': '109', 'bellsprout': '69', 'kingler': '99', 'dratini': '147', 'nidoqueen': '31', 'magneton': '82', 'psyduck': '54', 'omastar': '139', 'persian': '53', 'raichu': '26', 'electrode': '101', 'golem': '76', 'farfetchd': '83', 'moltres': '146', 'mew': '151', 'gyarados': '130', 'vaporeon': '134', 'doduo': '84', 'muk': '89', 'marowak': '105', 'wartortle': '8', 'gastly': '92', 'slowpoke': '79', 'rhydon': '112', 'cubone': '104', 'chansey': '113', 'geodude': '74', 'parasect': '47', 'pidgeotto': '17', 'venomoth': '49', 'victreebel': '71', 'nidorino': '33', 'nidorina': '30', 'rhyhorn': '111', 'tentacruel': '73', 'lapras': '131', 'grimer': '88', 'ninetales': '38', 'mankey': '56', 'scyther': '123', 'dodrio': '85', 'ekans': '23', 'seadra': '117', 'nidoking': '34', 'zapdos': '145', 'kabutops': '141', 'voltorb': '100', 'jolteon': '135', 'metapod': '11', 'growlithe': '58', 'rattata': '19', 'eevee': '133', 'snorlax': '143', 'kabuto': '140', 'poliwrath': '62', 'electabuzz': '125', 'ivysaur': '2', 'omanyte': '138', 'drowzee': '96', 'graveler': '75', 'caterpie': '10', 'diglett': '50', 'hypno': '97', 'machoke': '67', 'horsea': '116', 'exeggutor': '103', 'sandshrew': '27', 'golduck': '55', 'aerodactyl': '142', 'pidgeot': '18', 'exeggcute': '102', 'nidoran-f': '29', 'nidoran-m': '32', 'onix': '95', 'clefairy': '35', 'meowth': '52', 'dugtrio': '51', 'porygon': '137', 'slowbro': '80', 'alakazam': '65', 'zubat': '41', 'staryu': '120', 'weedle': '13', 'seel': '86', 'blastoise': '9', 'oddish': '43', 'flareon': '136', 'bulbasaur': '1', 'beedrill': '15', 'arbok': '24', 'ponyta': '77', 'butterfree': '12', 'tangela': '114', 'venonat': '48', 'charizard': '6', 'magikarp': '129', 'pikachu': '25', 'pidgey': '16', 'jynx': '124', 'seaking': '119', 'dewgong': '87', 'dragonair': '148', 'kakuna': '14', 'starmie': '121', 'shellder': '90', 'magmar': '126', 'weepinbell': '70', 'golbat': '42', 'mr-mime': '122', 'poliwag': '60', 'vileplume': '45', 'rapidash': '78', 'raticate': '20'}


def setup():
    elements = cgi.FieldStorage()
    m11 = elements.getvalue('moves11','Nothing')
    m12 = elements.getvalue('moves12','Nothing') 
    m13 = elements.getvalue('moves13','Nothing')
    m14 = elements.getvalue('moves14','Nothing')
    m21 = elements.getvalue('moves21','Nothing')
    m22 = elements.getvalue('moves22','Nothing') 
    m23 = elements.getvalue('moves23','Nothing')
    m24 = elements.getvalue('moves24','Nothing')
    m31 = elements.getvalue('moves31','Nothing')
    m32 = elements.getvalue('moves32','Nothing') 
    m33 = elements.getvalue('moves33','Nothing')
    m34 = elements.getvalue('moves34','Nothing')
    m41 = elements.getvalue('moves41','Nothing')
    m42 = elements.getvalue('moves42','Nothing') 
    m43 = elements.getvalue('moves43','Nothing')
    m44 = elements.getvalue('moves44','Nothing')
    m51 = elements.getvalue('moves51','Nothing')
    m52 = elements.getvalue('moves52','Nothing') 
    m53 = elements.getvalue('moves53','Nothing')
    m54 = elements.getvalue('moves54','Nothing')
    m61 = elements.getvalue('moves61','Nothing')
    m62 = elements.getvalue('moves62','Nothing') 
    m63 = elements.getvalue('moves63','Nothing')
    m64 = elements.getvalue('moves64','Nothing')
    data= open('data.txt','rU').read().split('\n')
    one = data[0].split(',') + [m11] + [m12] + [m13] + [m14]
    two = data[1].split(',') + [m21] + [m22] + [m23] + [m24]
    three = data[2].split(',') + [m31] + [m32] + [m33] + [m34]
    four = data[3].split(',') + [m41] + [m42] + [m43] + [m44]
    five = data[4].split(',') + [m51] + [m52] + [m53] + [m54]
    six = data[5].split(',') + [m61] + [m62] + [m63] + [m64]
    a1=data[6].split(',')
    a2=data[7].split(',')
    a3=data[8].split(',')
    a4=data[9].split(',')
    a5=data[10].split(',')
    a6=data[11].split(',')
    data = open('data.txt','w').write(','.join(one) + '\n' + ','.join(two) + '\n' + ','.join(three) + '\n' + ','.join(four) + '\n' + ','.join(five) + '\n' + ','.join(six) + '\n' +  ','.join(a1) + '\n' +','.join(a2) +'\n' + ','.join(a3) +'\n' + ','.join(a4) + '\n' +','.join(a5) +'\n' +','.join(a6))
    print HTML_HEADER + Top_HTML + one[0] + '</td><td>'+a1[0]+'</td></tr><tr><td><img src="' +pokemontoid[one[0].lower()]+'.png"/></td><td><img src="'+pokemontoid[a1[0].lower()]+'.png"/></td></tr><td>Type='+ one[1] + '</td><td>Type='+a1[1]+'</td></tr><tr><td>Status=' + one[2] + '</td><td>Status='+a1[2]+'</td></tr><tr><td>HP=' + one[3] + '</td><td>HP='+a1[3]+'</td></tr></table></center><br>'     
    print '<center><table border="1"><tr><td>' + '<input type="radio" value="' + one[-4] + '" name="move">'+one[-4]+'<br><input type="radio" value="' + one[-3] + '" name="move">'+one[-3]+'<br><input type="radio" value="' + one[-2] + '" name="move">'+one[-2]+'<br><input type="radio" value="' + one[-1] + '" name="move">'+one[-1]+'</td></tr>' + Bottom_HTML
setup()
    
    
