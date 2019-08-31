def noVowels(s):
  f = s.lower()
  b = ''
  for i in range (len(f)):
    if f[i] == 'a' or f[i] == 'e' or f[i] == 'i' or f[i] == 'o' or f[i] == 'u': 
      b = f.replace(f[i],'')
  return b

print noVowels('fish')
print noVowels('assdfe')

