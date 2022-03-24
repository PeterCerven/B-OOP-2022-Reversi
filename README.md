# Zadanie 2 - Reversi
B-OOP 2022

Vašou úlohou je naprogramovať hru Reversi ako oknovú aplikáciu v jazyku Java s pomocou knižníc AWT a Swing. Používateľské rozhranie aplikácie má pozostávať z:

* canvasu (alebo JPanel) - ktorý bude tvoriť hernú plochu
* bočné menu (jeho umiestnenie si zvoľte sami, teda môže byť aj hore, alebo dole)
## Pravidlá hry
### Základná pozícia a cieľ hry

Hra je známa aj pod názvom Othello. Hra bude začínať na hracej ploche veľkosti 6x6 polí, a bude možné jej veľkosť meniť (na obrázku je znázornená veľkosť 8x8).

![image](images/reversi.png)

Cieľom hry Reversi je mať na hracej ploche viac kameňov ako súper. Hra končí, ak sú všetky štvorce obsadené kameňmi alebo ak žiadny z hráčov nemôže urobiť platný ťah.

### Umiesťnovanie kameňov
Ak je hráč na ťahu, umiestni kameň svojej farby na hraciu plochu. Kamene nie je možné ukladať kdekoľvek - každým ťahom musíte zajať jeden alebo viac súperových kameňov, ktoré zmenia farbu a stávajú sa kameňmi aktuálneho hráča. Pokiaľ hráč nemôže v danej pozícii zajať žiaden súperov kameň, musí prenechať ťah súperovi.
Na začiatku hry sú 4 kamene umiestnené na hracej ploche, pričom dve sú bieleho a dve čierneho hráča (viď rozloženie na obrázku vyššie).

### Ako zajať súperove kamene
Hráč musí umiestniť kameň tak, aby obkľúčil svojimi dvoma kameňmi súvislý rad súperových kameňov, a to v ľubovoľnom smere (vodorovne, zvisle alebo uhlopriečne).

Ďalšie pravidlá:
* Pokiaľ sa hráč dostane do situácie, v ktorej nemôže urobiť ťah, je na to upozornený správou "Nemôžete urobiť ťah" a musí prenechať ťah súperovi.
* Predchádzajúce pravidlo neplatí, ak ani súper nemá možnosť urobiť ťah. V takomto prípade hra automaticky končí.

Zdroj:

[BrainKing](https://brainking.com/sk/GameRules?tp=9)

[Hra samotná](https://cardgames.io/reversi/)
## Požiadavky
Hru hrá jeden hráč proti počítaču. Farbu hráča, ktorý hru hrá si môžete zvoliť. Počítač môže hrať dvoma spôsobmi:

* Náhodný ťah z možných ťahov (Penalizácia 1 bod)
* Ťah, ktorým zajme súperovi najviac kameňov (Ak je takýchto ťahov viac môžete medzi nimi rozhodnúť ľubovoľným spôsobom)

Hra má byť hrateľná pomocou myši, keď myšou prejdem ponad pole na ktoré smieme umiesniť kameň, dané pole sa musí zvýrazniť. Všetky polia na ktoré vieme umiestniť kameň sú automaticky nejako vyznačené.
V menu sa má nachádzať:
* informácia o tom, ktorý hráč je na ťahu, poprípade keď hra skončí, ktorý hráč vyhral.
* tlačidlo, ktorým vieme hru zresetovať.
* informáca hovoriaca o aktuálnom rozmere hracieho plánu.
* komponent pomocou ktorého je možné zmeniť veľkosť hracej plochy (iba na hodnoty 6,8,10,12). Konkrétny komponent si môžete zvoliť sami, napríklad jeden z: Slider, JTextField, JComboBox

Stlačením klávesy R na klávesnici vieme tiež hru reštartovať, a pomocou klávesy ESC vypnúť.


## Hodnotenie

Zadanie je hodnotené 15 bodmi. 5 bodov je za funkčnosť zadania, 5 bodov za Princípy OOP a 5 bodov za správne používanie knižnice SWING. **Odovzdaný program musí byť skompilovateľný, inak je
hodnotený 0 bodmi**. Skompilovateľnosť zadania kontroluje aj github pipeline. Hlavný dôraz v hodnotení sa kladie na objektový prístup a princípy,
okrem iného:

* vhodné pomenovanie tried a metód v jednotnom jazyku (názvy tried s veľkým počiatočným písmenom, názvy metód s malým),
* vhodné použitie modifikátorov prístupu (public, private, poprípade protected) na obmedzenie prístupu k metódam a atribútom,
* využitie dedenia a polymorfizmu,
* použitie výnimiek na ošetrenie nedovoleného správania (nehádzať a nezachytávať všeobecnú triedu Exception),
* nepoužívajte nested classy,
* vo vašich triedach nevytvárajte statické metódy ani nekonštantné statické premenné (v zadaní nie sú potrebné),
* v hlavnej triede (main) nevytvárajte žiadnu logiku, iba vytvorte nový objekt.
* vo svojom riešení môžete použiť knižnicu lombok a jej anotácie. Potrebná dependencia je už pridaná v _pom.xml_ súbore.

Niektoré z vecí, za ktoré sme minulý rok strhli po 0,5 - 1 bode:

* TODO

Prípadne sú pri nedostatočnej implementácií struhnuté body za OOP za nedostatočnú implementáciu.

**Pri zadaní sa kontroluje originalita zadaní, a všetky zadania so zhodou vyššou ako 80% sú hodnotené 0 bodmi.**

## Odovzdávanie
Zadanie si naklonujte z repozitára zadania výhradne pomocou poskytnutej linky cez GitHub Classroom (pokiaľ si vygenerujete vlastný repozitár pomocou tlačidla "Use this template" z template repozitára, my váš repozitár neuvidíme a nebudeme ho hodnotiť!). Svoje vypracovanie nahrajte do pre vás vytvoreného repozitára pre toto zadanie pomocou programu Git (git commit + git push).
Skontrolujte si, či sa váš repozitár nachádza pod skupinov **Interes-Group**, inak nemáme prístup ku vášmu repozitáru a zadanie sa nepovažuje za odovzdané. Vypracovanie môžete "pusho-vať" priebežne. Hodnotiť sa bude iba _master_ branch. Zadanie je nutné vypracovať do **15.4.2022 23:00**.

V projekte upravujte iba súbory v priečinku _src/main_ a jeho podpriečinkoch. Ostatné súbory je upravovať zakázané (predovšetkým súbory _pom.xml_, súbory obsahujúce github pipeline-y a súbory obsahujúce automatizované testy).

Vo svojom github účte si nastavte svoje meno alebo AIS login (settings > profile > name), aby bolo možné priradiť riešenie ku študentovy. **Pokiaľ nebude možné spárovať študenta s riešením je zadanie hodnotené 0 bodmi!**
