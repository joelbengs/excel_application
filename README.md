# EDAF60 - grupp 46: XL

+ Joel Bengs (jo5531be-s)
+ Gustaf Jonson Stamfält (gu3505jo-s)
+ Victor Pekkari (vi8011pe-s)
+ Henrik Vester (he5834ve-s)

## Svar på designfrågor

![Model](images/model.png)

+ **A3**: Förklara i bara några få ord vad var och en av klasserna
          `SlotLabel`, `SlotLabels`, `Editor`, `StatusLabel`,
          `CurrentLabel` och `XL` i den utdelade koden gör.

  Ert svar på A3.

+ **A4**: Användningsfall: Någon skriver talet 42 i `Editor`, vad
          skall hända innan värdet syns i vyn (dvs vilken väg skall
          värdet gå genom M, V och C)?

  Ert svar på A4.

+ **B2**: En cell kan innehålla antingen en kommentar eller ett
          uttryck hur modellerar vi det i Javakod?

  Ert svar på B2

+ **B4**: Vilka klasser, förutom dem i `expr`-paketet, kommer vi att
          behöva i vår modell?

  Ert svar på B4

+ **C1**: När ett uttryck som innehåller en adress beräknas använder
          vi en `Environment` -- varför?

  Ert svar på C1

+ **D1**: Vilka klasser i modellen måste vara kända av vårt GUI?

  Ert svar på D1

+ **D2**: När vårt GUI hämtar 'värdet' i en cell, för att visa det i
          rutnätet, vilket värde är det vi egentligen vill ha, och
          vilken typ bör det ha?

  Ert svar på D2

+ **E4**: Allmänt: vilket paket bör upptäcka fel?

  Ert svar på E4

+ **E5**: Allmänt: vilket paket bör hantera fel, och hur gör vi det?

  Ert svar på E5

+ **F1**: Vilken slags synkronisering (_Flow Synchronization_ eller
          _Observer Synchronization_) vill gruppen använda för
          kommunikationen mellan M och V/C?

  Ert svar på F1

+ **F2**: Hur håller programet reda vilket som är den aktuella
          cellen? Svaret på denna fråga beror på hur ni
          implementerar er Controller.

  Ert svar på F2

+ **F3**: Hur triggas uppdateringar i ert GUI? Svaret på denna fråga
          beror av hur ni implementerar er Controller.

  Ert svar på F3

## Att köra programmet

För att köra programmet kan man skriva:

~~~{.sh}
./gradlew run
~~~

i projektets rot-katalog.
