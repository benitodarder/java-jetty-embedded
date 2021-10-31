# jetty-embedded-core

Provides definitions and basic abstract implementations:

- Base, common base definitions and functionality:
    * Ping controller for service up checking.
    * Base exceptions.
    * Web, providing shutdown hook and abstract servers:
        - No SSL
        - SSL
        - HTML page server
    * Configuration interfaces
- DAO, provides abstract factory and DAO implementation, and configuration.
- Model data (persistence) and domain
- Utils, file access, JSON...