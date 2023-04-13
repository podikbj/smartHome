Smart home

Funkční požadavky

F1 - je ve tride Main
F2 - gettery a settery jsou v tridach ADevice, AirConditioner, BlindsPlastik, BlindsWood, BurglarAlarm,
CoffeeMachine, ElectronicLock, GasBoiler, GasLeakDetector, Sensor, SmokeAlarm, WashingMachine, 
WaterLeakDetector

F3 - atributy trid   AirConditioner, BlindsPlastik, BlindsWood, BurglarAlarm,
CoffeeMachine, ElectronicLock, GasBoiler, GasLeakDetector, Sensor, SmokeAlarm, WashingMachine, 
WaterLeakDetector.

F4 - gettery a settery jsou v tridach ADevice, AirConditioner, BlindsPlastik, BlindsWood, BurglarAlarm,
CoffeeMachine, ElectronicLock, GasBoiler, GasLeakDetector, Sensor, SmokeAlarm, WashingMachine, 
WaterLeakDetector

F5 - trida Person: metody makeStep(), visitDevice. Tridy Cat, Dog, Parrot: metoda makeStep().
AirConditioner, BlindsPlastik, BlindsWood, BurglarAlarm,
CoffeeMachine, ElectronicLock, GasBoiler, GasLeakDetector, Sensor, SmokeAlarm, WashingMachine - metoda makeStep.

F6 - trida AComponent umožní ukládání všech prvků konfigurace do jednoho stromu. 
Generace eventu - jsou v tridach  
AirConditioner, BlindsPlastik, BlindsWood, BurglarAlarm, CoffeeMachine, ElectronicLock, GasBoiler, 
GasLeakDetector, Sensor, SmokeAlarm, WashingMachine: simulateEvent, notifyHandler, checkDeviceStatus.
Person metoda Update.

F7 Person metoda Update, AirConditioner, BlindsPlastik, BlindsWood, BurglarAlarm, CoffeeMachine, ElectronicLock, GasBoiler, 
GasLeakDetector, Sensor, SmokeAlarm, WashingMachine. 
Tridy HandlerBreaking(rozbití zařízení), HandlerBurglarAlarm, 
HandlerGasLeak, HandlerSmokeAlarm, HandlerWaterLeak. 

F8 Trida Repors. Včetně finančního vyčíslení - neni implementovano.

F9 trida ADevice, metoda getRepairManual.

F10 Tridy Ski, Bike, AComponent

 Vhodné design patterny
 
Pattern Iterator bude implementován přes iterator kolekce ArrayList<IVisited >, trida Main, str 196.
Pattern Decorator bude implementován abstractnimi třídami BasicWindow, BlindsDecorator pomoci trid WindowBig, WindowSmall, BlindsPlastik, BlindsWood.
Umoznuje rozsirit funkcnost tridy WindowBig a WindowSmall tridami BlindsPlastik a BlindsWood.  
Pattern State bude implementován třídami Active, Idle, Blocked, které dědí od třídy State. Implementuje stav spotřebiče.
Pattern Composite bude implementován třídou AComponent. Umožní ukládání všech prvků konfigurace do jednoho stromu.
Pattern Singlton bude implementován třídou ReusablePool.
Pattern Visitor bude implementován rozhraními IVisitor, IVisited pomoci trid ADevice a Person. 
Umožňuje obejít všechny spotřebiče a provést nějaký update.
Pattern ChainOfResponsibility bude implementován třídami Handler, HandlerBulgarAlarm, HandlerWaterLeak, HandlerGasLeak. 
Umožňuje zpracování rekvestu od ADevice pokud dojde k nějaké havárie a odesílání messagu do IObserveru.
Pattern Object Pool bude implementován třídou ReusablePool.
Partially persistent data structure: ukladani eventu, activities do ArrayList. Trida ReusablePool, SmartHomeFactory.
Pattern Observer bude implementován rozhraním IObserver pomoci tridy Person. Odbavi eventy na zarizeni.
Pattern Factory bude implementován tridou SmartHomeFactory. Vytvori vsechni instance konfigurace.
Pattern Lazy Initialization je v tride Main pri vytvareni nove instance.


Nefunkční požadavky
●	Není požadována autentizace ani autorizace - ANO
●	Aplikace může běžet pouze v jedné JVM      - ANO  
●	Aplikaci pište tak, aby byly dobře schované metody a proměnné,  ANO
které nemají být dostupné ostatním třídám. 
Vygenerovný javadoc by měl mít co nejméně public metod a proměnných.
●	Reporty jsou generovány do textového souboru : 4 soubory jsou uvnitr projectu ve formatu .txt.
●	Konfigurace domu, zařízení a obyvatel domu může být nahrávána přímo 
z třídy nebo externího souboru (preferován je json) : ve tride Main


Požadované výstupy
●	Design ve formě use case diagramů, class diagramů 
a stručného popisu jak chcete úlohu realizovat :  soubor smart_home_documentation. docx je uvnitr projectu.
●	Veřejné API - Javadoc vygenerovaný pro funkce, kterými uživatel pracuje s vaším software : 
soubor smart_home_documentation. docx je uvnitr projectu.
●	Dvě různé konfigurace domu a pro ně vygenerovány reporty za různá období. 
Minimální konfigurace alespoň jednoho domu je: 6 osob, 3 zvířata, 8 typů spotřebičů, 
20 ks spotřebičů, 6 místností, jedny lyže, dvě kola.  Dve konfigurace jsou v tride Main.


///////////////////////////
1. Class diagram se nachazi v souboru diagram.rtf

proc jina konvence od Javy: podtrzitka: smart_home, //opraveno
2. .iml soubor target, META-INF, vse do ignoregi souboru, to nepatri do repa, //opraveno

3. reporty do zvlastniho souboru, //opraveno

4. class diagram obsahuje jak atrbiuty tak vztahy pomoci cary co ze je redundatni nechat jen cary, // opavila ale pouze pro 
tridy se vztachem, jako je slozeni nebo agregace

5. ADevice ma atribut na State ale to melo byt asi IState, //opraveno

6. obecne struktura projektu i repa, balicky, tohle je pak gulas :), //opraveno

7. javadoc chybi, //opraveno/ pridala jsem pouze pro public metody

8. naformatovat kod, //opraveno
Handler komentare smazat je to produkcni kod :), //opraveno

9. indices ma byt typu jen List, opraveno

10. chybi mi tam metoda hasNext jako u iteratoru, //opraveno, implementovano ve tride IVisitedIterator
implementace se totiz muze menit a proto je potreba ji tam mit, 

11. pouzivat logger, //opraveno, implementovano ve tridech Reports, Dog
