
## Chapter 3 - Jobs
**[Back to Contents](/README.md)**

Many things are happening in the world at the same time, and need to be organized in some manner. This ranges from Trade Routes, Alliance Agreements to simple Automated Resource Movement needed for construction. All these tasks are organized into `Jobs`, which are each executed on each tick.

Jobs can be thought of as functions which run over multiple ticks. The Job itself is responsible for calling `complete()` when it is done, which will free it from the jobs list.

Jobs are able to maintain state which persists between calls to its `execute()` method.

