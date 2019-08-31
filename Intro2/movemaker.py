def movelist(L):
    moveraw = open(L,'rU').read().split()
    L2 = []
    L3 = []
    for i in moveraw:
        L2.append(i.split(','))
    for i in L2:
        L3.append(i[0:2])
    return L3[1:166]

movelist('moves.csv')

d = [['1', 'pound'], ['2', 'karate-chop'], ['3', 'doubleslap'], ['4', 'comet-punch'], ['5', 'mega-punch'], ['6', 'pay-day'], ['7', 'fire-punch'], ['8', 'ice-punch'], ['9', 'thunderpunch'], ['10', 'scratch'], ['11', 'vicegrip'], ['12', 'guillotine'], ['13', 'razor-wind'], ['14', 'swords-dance'], ['15', 'cut'], ['16', 'gust'], ['17', 'wing-attack'], ['18', 'whirlwind'], ['19', 'fly'], ['20', 'bind'], ['21', 'slam'], ['22', 'vine-whip'], ['23', 'stomp'], ['24', 'double-kick'], ['25', 'mega-kick'], ['26', 'jump-kick'], ['27', 'rolling-kick'], ['28', 'sand-attack'], ['29', 'headbutt'], ['30', 'horn-attack'], ['31', 'fury-attack'], ['32', 'horn-drill'], ['33', 'tackle'], ['34', 'body-slam'], ['35', 'wrap'], ['36', 'take-down'], ['37', 'thrash'], ['38', 'double-edge'], ['39', 'tail-whip'], ['40', 'poison-sting'], ['41', 'twineedle'], ['42', 'pin-missile'], ['43', 'leer'], ['44', 'bite'], ['45', 'growl'], ['46', 'roar'], ['47', 'sing'], ['48', 'supersonic'], ['49', 'sonicboom'], ['50', 'disable'], ['51', 'acid'], ['52', 'ember'], ['53', 'flamethrower'], ['54', 'mist'], ['55', 'water-gun'], ['56', 'hydro-pump'], ['57', 'surf'], ['58', 'ice-beam'], ['59', 'blizzard'], ['60', 'psybeam'], ['61', 'bubblebeam'], ['62', 'aurora-beam'], ['63', 'hyper-beam'], ['64', 'peck'], ['65', 'drill-peck'], ['66', 'submission'], ['67', 'low-kick'], ['68', 'counter'], ['69', 'seismic-toss'], ['70', 'strength'], ['71', 'absorb'], ['72', 'mega-drain'], ['73', 'leech-seed'], ['74', 'growth'], ['75', 'razor-leaf'], ['76', 'solarbeam'], ['77', 'poisonpowder'], ['78', 'stun-spore'], ['79', 'sleep-powder'], ['80', 'petal-dance'], ['81', 'string-shot'], ['82', 'dragon-rage'], ['83', 'fire-spin'], ['84', 'thundershock'], ['85', 'thunderbolt'], ['86', 'thunder-wave'], ['87', 'thunder'], ['88', 'rock-throw'], ['89', 'earthquake'], ['90', 'fissure'], ['91', 'dig'], ['92', 'toxic'], ['93', 'confusion'], ['94', 'psychic'], ['95', 'hypnosis'], ['96', 'meditate'], ['97', 'agility'], ['98', 'quick-attack'], ['99', 'rage'], ['100', 'teleport'], ['101', 'night-shade'], ['102', 'mimic'], ['103', 'screech'], ['104', 'double-team'], ['105', 'recover'], ['106', 'harden'], ['107', 'minimize'], ['108', 'smokescreen'], ['109', 'confuse-ray'], ['110', 'withdraw'], ['111', 'defense-curl'], ['112', 'barrier'], ['113', 'light-screen'], ['114', 'haze'], ['115', 'reflect'], ['116', 'focus-energy'], ['117', 'bide'], ['118', 'metronome'], ['119', 'mirror-move'], ['120', 'selfdestruct'], ['121', 'egg-bomb'], ['122', 'lick'], ['123', 'smog'], ['124', 'sludge'], ['125', 'bone-club'], ['126', 'fire-blast'], ['127', 'waterfall'], ['128', 'clamp'], ['129', 'swift'], ['130', 'skull-bash'], ['131', 'spike-cannon'], ['132', 'constrict'], ['133', 'amnesia'], ['134', 'kinesis'], ['135', 'softboiled'], ['136', 'hi-jump-kick'], ['137', 'glare'], ['138', 'dream-eater'], ['139', 'poison-gas'], ['140', 'barrage'], ['141', 'leech-life'], ['142', 'lovely-kiss'], ['143', 'sky-attack'], ['144', 'transform'], ['145', 'bubble'], ['146', 'dizzy-punch'], ['147', 'spore'], ['148', 'flash'], ['149', 'psywave'], ['150', 'splash'], ['151', 'acid-armor'], ['152', 'crabhammer'], ['153', 'explosion'], ['154', 'fury-swipes'], ['155', 'bonemerang'], ['156', 'rest'], ['157', 'rock-slide'], ['158', 'hyper-fang'], ['159', 'sharpen'], ['160', 'conversion'], ['161', 'tri-attack'], ['162', 'super-fang'], ['163', 'slash'], ['164', 'substitute'], ['165', 'struggle']]

def dictmake(L):
    temp = {}
    for i in L:
        temp[int(i[0])] = i[1]
    return temp

print dictmake(d)
