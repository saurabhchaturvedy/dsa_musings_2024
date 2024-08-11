# Key Differences Between Singleton and Immutable Object

| Feature                     | Singleton                                    | Immutable Object                           |
|-----------------------------|---------------------------------------------|-------------------------------------------|
| **Purpose**                 | Ensure a single instance of a class         | Prevent modification of object state      |
| **Instance Control**        | One instance globally                        | Multiple instances possible                |
| **State**                   | May have mutable state                      | Always has immutable state                 |
| **Thread Safety**           | Needs to handle synchronization              | Thread-safe by design                     |
| **Usage Context**           | Shared resources or services                 | Value types or fixed data representations  |
