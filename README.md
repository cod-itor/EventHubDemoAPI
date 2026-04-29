# WebSocket Live View + Presence Add-on

This add-on layers WebSocket presence tracking on top of the existing JWT login system.

## WebSocket Setup

- STOMP endpoint: `/ws` (SockJS enabled)
- Application prefix: `/app`
- Broker topics: `/topic/live-views`, `/topic/admin-presence`

## JWT Interceptor

The `WebSocketJwtInterceptor` reads the `Authorization` header during the STOMP `CONNECT` frame, validates the JWT, and sets the authenticated user into the session so Spring can expose it as a Principal.

## Presence Tracking

- Connect event increments the live view counter and logs `LOGIN` to `user_activity_logs`.
- Disconnect event decrements the counter and logs `LOGOUT`.
- Every change broadcasts both topics.

## Required Headers

The browser should send `Authorization: Bearer <token>` in the STOMP CONNECT headers.

## Local Notes

- PostgreSQL connection details live in `application.properties`.
- JPA is configured with `ddl-auto=update` to create `user_activity_logs` automatically.
