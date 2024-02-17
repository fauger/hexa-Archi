# Hexagonal Architecture kotlin et Springboot

## Explications et notes

### Module Domaine avec les classes métier et des adaptateurs API et SPI 
API (application programming interface) : Point d'accés pour obtenir un service. Dans notre projet l'api est une interface qui est appelé par le controlleur pour récupérer le resultat du service

SPI (Service provider interface) : Provider à implementé. Dans notre projet le SPI est une interface nécessaire à notre domaine pour recupérer les datas metiers. L'implementation concréte se trouve dans ke module infra.

Le domaine n'a pas de dependance à spring.

### Module infra qui implémente l'API et le SPI

Le module infra expose une API REST `FleetController` via les annotations spring dédiées

`FleetController` apelle la classe métier via son interface. L'implementation (metier) est injectée via une annotation custom `@Domain` et scannée par notre module infra via la configuration définie dans `DomainConfiguration`

Le module infra implémente aussi le SPI dans la classe `SwapiClient`. La classe est annotée `@Component` ce qui permet à spring de l'injecter dans la classe métier

### Un mot sur les tests
Dans le domaine métier on test la classe métier, rien de spécial ici

Dans le domaine infra, on test le **controlleur** via `@WebMvcTest` qui permet de creer un context spring pour le controlleur à tester et `@AutoConfigureMockMvc` qui permet de configurer mockMvc (mock servlet) utilisé pour faire et verifier les appels. On utilise aussi l'annotation `@Import(DomainConfiguration::class)` pour importer des données stubbés dans le domaine pour notre SPI (`StarShipInventoryStub`)

Toujours dans le domaine infra on test note **implémentation du SPI** qui est un client REST (RestTemplate) avec l'annotation `@RestClientTest` qui permet de configurer un MockRestServiceServer pour mocker l'api client

## Source

Conference de julien Topcu : https://www.youtube.com/watch?v=xUlUEQL7i2E

Source gitlab orignal : https://gitlab.com/beyondxscratch/hexagonal-architecture-java-springboot/-/tree/main?ref_type=heads
