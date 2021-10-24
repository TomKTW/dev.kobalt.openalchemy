OpenAlchemy
===========

This is a wrapper for Alchemy Deluxe game made by PopCap Games (back when they were making good software).

## Requirements

- Java 8 Runtime

- Alchemy Deluxe 1.6 game resources - Place at least "images", "music" and "sounds" folders in "./assets" folder. 

## Implementation

Only strategy mode with discarding up to 3 runes is implemented.

Difficulty should be implemented the same way as it is in the game.

Chance for stone and skull to spawn is 5%.

Most of the animations and particles are not implemented, just basic stuff.

Options don't contain any setting to change, it only contains some details about the program itself.

Module music player is more or less just implemented as a trainwreck.

This is more or less still experimental as I'm trying to figure out how to use LibGDX on my own, don't expect seeing any quality comments (there aren't any).

Considering this whole thing had 3-4 rewrite attempts and things, it's most likely leaking memory in some way.

## License 

This program is licensed under AGPL-3.0.

## Third party components

- [Kotlin programming language](https://kotlinlang.org) - Apache-2.0
- [LibGDX game framework](https://libgdx.com) - Apache-2.0
- [LibKTX extensions](https://libktx.github.io) - CC0-1.0
- [JavaMod module player](https://sourceforge.net/projects/javamod/files/javamod) - GPL 3.0