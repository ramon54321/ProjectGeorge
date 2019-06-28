
## Chapter 2 - Nations
**[Back to Contents](/README.md)**

Nations are simply logical containers for hexes. Note this is at a logical level and not a data level. A Nation can also be described as the result of all the hexes it possesses.

#### Control

Nations are the central point of control for a player. A `Nation` instance contains a list of clients, which are able to control the nation.

#### Hex Possession

Central to the logic of a Nation are the Hexes in it's possession. Without possessing Hexes, a Nation can has very limited ability.

#### Resources

A nation's resources is the sum off all the resources present on all the Hexes which are in possession of the nation. However, it is possible that the resources are not obtainable due to specific situations present on the tile, for example Disasters, Political Issues or a Disputed Claim on the Hex.

#### Developments

Hexes under a nation's control can be developed.

- Roads
- Trade Posts
- Farms
- Mines
- Factories
- Barracks
- Airports
- Power Stations
- Fortifications

Some Hexes allow specific developments which are not available on all hexes.

- Docks (Coastal Hexes)
- Oil Pumps (Oil Rich Hexes)

##### Construction

Developments need to be constructed and in some cases, maintained. Both require resources, which need to be present on the tile in order for the construction to take place. Resources will automatically be sourced from nearby hexes if the hexes are connected by the nation's hexes.

