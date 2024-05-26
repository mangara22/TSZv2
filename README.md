# TSZv2
An improved implementation of my [TextSafariZone](https://github.com/mangara22/TextSafariZone).

## What's improved?
  1. There are *2* improved implementations[^1] of my **TextSafariZone**, each has a different branch.
      1. `Console`: contains the improved TextSafariZone, with the only noticeable change being in the back-end where it's far more organized.
      2. `GUI`: contains the GUI implementation, built off of the `Console` branch. This uses `Java Swing` components.
         - You can find a link to the Javadocs for the GUI implementation [here](https://mangara22.github.io/TSZv2/package-summary.html).
  3. Region data, such as *Kanto*, *Johto*, or *Mixed*, from before have been replaced with `.txt` files containing the first 50 Pokémon from each of the 9 generations.
      - This was done using a `Python` script to scrape data from [this website](https://pokemondb.net/pokedex/national) using `BeautifulSoup`
      - You can find the link to the repo [here](https://github.com/mangara22/PokemonWebScraping)
  4. The original implementation used one class for **everything**, so there are now multiple classes, each meant for one specific purpose (single responsibility).  
[^1]: Note that some features have been removed/revised, such as display rates, but the core functionality still exists for both implementations.

## Screenshots:
### Console version of TextSafariZonev2
<img width="1066" alt="consoleTSZ" src="https://github.com/mangara22/TSZv2/assets/125520397/f9fdb4ef-ffe1-4936-9b84-679bf168c04c">

### GUI Version of TextSafariZonev2
<img width="1440" alt="GUITSZ" src="https://github.com/mangara22/TSZv2/assets/125520397/a2077f47-7037-4e18-999c-1dec11923e61">

