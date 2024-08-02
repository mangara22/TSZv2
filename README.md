# TSZv2
<div>
  <img src="https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExMjFmbjRtY3Jnc2x0MzRpNmVnajkyNGU1aTFya21nMDBuZWp1c252ayZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/uWLJEGCSWdmvK/giphy.gif" width="300">
</div>

An improved implementation of my [TextSafariZone](https://github.com/mangara22/TextSafariZone), which was a text-based representation of the [Safari Zone](https://bulbapedia.bulbagarden.net/wiki/Safari_Zone) from the Pokémon games, where the player can explore and catch wild Pokémon.

## What's improved?
  1. There are *2* improved implementations[^1] of my **TextSafariZone**, each has a different branch.
      1. `Console`: contains the improved TextSafariZone, with the only noticeable change being in the back-end where it's far more organized in that each class has a different purpose.
      2. `GUI`: contains the GUI implementation, built off of the `Console` branch. This uses `Java Swing` components.
         - You can find a link to the Javadocs for the GUI implementation [here](https://mangara22.github.io/TSZv2/package-summary.html).
  3. Region data, such as *Kanto*, *Johto*, or *Mixed*, from before have been replaced with `.txt` files containing the first 50 Pokémon from each of the 9 generations.
      - This was done using a `Python` script to scrape data from [this website](https://pokemondb.net/pokedex/national) using `BeautifulSoup`.
      - You can find the link to the repo [here](https://github.com/mangara22/PokemonWebScraping).
  4. The original implementation used one class for **everything**, so there are now multiple classes, each meant for one specific purpose (single responsibility).  
[^1]: Note that some features have been removed/revised, such as display rates, but the core functionality still exists for both implementations.

## Screenshots:
### Console version of TextSafariZonev2
<img width="1066" alt="consoleTSZ" src="https://github.com/mangara22/TSZv2/assets/125520397/f9fdb4ef-ffe1-4936-9b84-679bf168c04c">

---

### GUI Version of TextSafariZonev2
https://github.com/mangara22/TSZv2/assets/125520397/eaaa7645-026c-4f69-a38d-c1a6a74398ad
