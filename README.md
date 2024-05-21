# TSZv2
An improved implementation of my [TextSafariZone](https://github.com/mangara22/TextSafariZone).

## What's improved?
  1. Region data, such as *Kanto*, *Johto*, or *Mixed*, have been replaced with `.txt` files each containing the first 50 Pokémon from each of the 9 generations.
      - This was done using a python script to scrape data from [this website](https://pokemondb.net/pokedex/national) using `BeautifulSoup`
      - You can find the link to the repo [here](https://github.com/mangara22/PokemonWebScraping)
  2. The original implementation used one class for **everything**, so there are now multiple classes, each meant for one specific purpose.  
