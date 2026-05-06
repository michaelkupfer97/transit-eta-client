# transit-eta-client

A Java client for fetching real-time bus ETA (Estimated Time of Arrival) from multiple transit data providers.

## Overview

This project demonstrates a clean, extensible architecture for querying bus arrival times across different API providers. It uses the **Strategy Pattern** to support multiple providers interchangeably, with a central manager that routes requests to the correct one.

## Project Structure

```
transit-eta-client/
├── src/
│   ├── Main.java
│   └── TransitEtaClient/
│       ├── INextBusManager.java       # Manager interface
│       ├── NextBusManager.java        # Routes requests to providers
│       ├── INextBusProvider.java      # Provider interface (Strategy)
│       ├── entities/
│       │   └── StopEta.java           # ETA data model
│       └── providers/
│           ├── HttpReq.java           # Shared HTTP utility (GET & POST)
│           ├── Provider1.java         # Provider 1 — REST GET API
│           └── Provider2.java         # Provider 2 — REST POST API
```

## Architecture

The design follows the **Strategy Pattern**:

- `INextBusProvider` — interface that every provider implements
- `Provider1` / `Provider2` — concrete implementations, each handling a different API format
- `NextBusManager` — holds a map of providers and delegates calls by provider ID
- `HttpReq` — shared HTTP utility injected into providers (avoids code duplication)

This makes it easy to add a new provider without changing any existing code — just implement `INextBusProvider` and register it in the manager.

## How to Run

1. Clone the repository:
```bash
git clone https://github.com/michaelkupfer97/transit-eta-client.git
```

2. Open the project in IntelliJ IDEA.

3. Run `Main.java`.

## Example Output

```
stopId: 1234
eta: Mon Jan 01 10:30:00 IST 2025
stopId: 5678
eta: Mon Jan 01 10:35:00 IST 2025
```

## Technologies

- Java 11+
- `java.net.http.HttpClient` (built-in HTTP client)
- Regex-based JSON parsing

## Design Decisions

- **`HttpReq` as a shared utility** — providers receive it via constructor injection, making them easy to test and avoiding repeated HTTP setup code.
- **`NextBusManager` uses a `Map<Integer, INextBusProvider>`** — allows dynamic registration of providers at runtime.
- **Graceful error handling** — if a provider fails, the manager returns an empty list instead of crashing.