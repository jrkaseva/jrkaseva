#####################################################
#Author: Joakim Kaseva                              #
#Automated menu maker in Finnish                    #
#                                                   #
#                                                   #
#                                                   #
#                                                   #
#                                                   #
#####################################################
import sys

#Gets size of wanted menu
def makeValikko():
    lista = []
    print("Tämä ohjelma luo valmispohjan valikkopohjaiselle python-ohjelmalle.")
    print("Ohjeet:\nEsimerkki valikko:\n\tLaskin\n\t1. summa\n\t2. erotus\n\t...\n\t0 lopeta")
    print("Ensimmäiseksi anna haluttujen toimintojen määrä.\nToiseksi anna ohjelman nimi ja ohjelman kuvaus.")
    num = input("Anna valikon koko: ")
    try:
        num = int(num)
    except ValueError:
        print("Antamasi syöte '" + num + "' ei ollut numero.")
        sys.exit(0)
    getValikko(num, lista)
    retValikko(lista)

#Gets options for the menu
def getValikko(num, arr):
    for i in range(1, num + 1):
        arr.append(input(str(i) + ". toiminto: "))

#Creates the .py file as per requested
def retValikko(arr):
    kirjoitukset = []
    file = None
    while file is None:
        fN = input("Anna ohjelman nimi: ")
        fN = muunna(fN) + ".py"
        try:
            file = open(fN, "x", encoding="utf-8")
        except OSError:
            print("On jo tiedosto nimeltään '" + fN + "'.")
            overwrite = input("Haluatko kirjoittaa olemassa olevan tiedoston päälle?(y/n): ").lower().strip() == 'y'
            if (overwrite):
                file = open(fN, "w", encoding="utf-8")
    uusiMain(kirjoitukset)
    uusiValikko(kirjoitukset, arr)
    uusiToteuta(kirjoitukset, arr)
    uudetAliOhjelmat(kirjoitukset, arr)
    for i in kirjoitukset:
        file.write(i)
    file.close()
    print("Tiedosto '" + fN + "' luotu. Muista täyttää ja muokata ohjelma valmiiksi loppuun!")

#The writing for the "def main()" in the .py file
def uusiMain(arr):
    kuvaus = input("Mitä ohjelma tekee?(Tämä ohjelma...): ")
    arr.append("###########################################\n#THIS PROGRAM WAS CREATED BY JOAKIM KASEVA#\n###########################################\n\n")
    arr.append("def main():\n\t")
    arr.append("print(\"" + kuvaus + "\")\n\t")
    arr.append("valinta = valikko()\n\t")
    arr.append("while True:\n\t\t")
    arr.append("if valinta == 0:\n\t\t\t")
    arr.append("break\n\t\t")
    arr.append("toteuta(valinta)\n\t\t")
    arr.append("valinta = valikko()\n\t")
    lopetus = "Kiitos ohjelman käytöstä."
    arr.append("print(\"" + lopetus + "\")\n\n")

#The writing for the "def valikko()" - aka the menu
def uusiValikko(arr, arr2):
    arr.append("def valikko():\n\t")
    arr.append("arr = " + str(arr2) + "\n\t")
    arr.append("print(\"Käytettävissä olevat toiminnot:\")\n\t")
    arr.append("for i in range(1, len(arr) + 1):\n\t\t")
    arr.append("print(str(i) + \") \" + arr[i - 1])\n\t")
    arr.append("print(\"0) Lopeta\")\n\t")
    arr.append("x = int(input(\"Valintasi: \"))\n\t")
    arr.append("return x\n\n")

#The writing for the "def toteuta" - aka which option has been selected
def uusiToteuta(arr, toiminnot):
    arr.append("def toteuta(num):\n\t")
    count = 1
    for i in toiminnot:
        if count == 1:
            arr.append("if num == " + str(count) + ":\n\t\t")
        else:
            arr.append("elif num == " + str(count) + ":\n\t\t")
        i = muunna(i)
        arr.append(i + "()\n\t")
        toiminnot[count - 1] = i
        count += 1
    arr.append("else:\n\t\tprint(\"Valintaa ei tunnistettu, yritä uudestaan.\")\n\n")

#Changes the options into lowercase and code friendly
def muunna(mjono):
    mjono = ''.join(ch for ch in mjono if ch.isalnum())
    mjono = mjono.lower()
    mjono = mjono.replace("ä", "a")
    mjono = mjono.replace("ö", "o")
    if mjono[0:1:1].isdigit():
        mjono = "_" + mjono
    return mjono

#Creates the "def *" where * are the options
def uudetAliOhjelmat(arr, toiminnot):
    for i in toiminnot:
        arr.append("def " + i + "():\n\t")
        arr.append("#Fill in\n\t")
        arr.append("return 0\n\n")
    arr.append("main()\n")

makeValikko()
