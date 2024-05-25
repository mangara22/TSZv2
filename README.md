# TSZv2
An improved implementation of my [TextSafariZone](https://github.com/mangara22/TextSafariZone).

## What's improved?
  1. There are actually 2 improved implementations[^1] of my **TextSafariZone**, each has a different branch.
      1. `Console`: contains the improved TextSafariZone, with the only noticeable change being in the back-end where it's far more organized.
      2. `GUI`: contains the GUI implementation, built off of the `Console` branch. This uses `Java Swing` components.
  3. Region data, such as *Kanto*, *Johto*, or *Mixed*, from before have been replaced with `.txt` files containing the first 50 Pokémon from each of the 9 generations.
      - This was done using a `Python` script to scrape data from [this website](https://pokemondb.net/pokedex/national) using `BeautifulSoup`
      - You can find the link to the repo [here](https://github.com/mangara22/PokemonWebScraping)
  4. The original implementation used one class for **everything**, so there are now multiple classes, each meant for one specific purpose (single responsibility).  
[^1]: Note that some features have been removed/revised, such as display rates, but the core functionality still exists for both implementations.
