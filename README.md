# instagram-parody

## Requirements

### Functional requirements
1. Allow new users to create accounts and existing users to login.
2. Allow users to chat with each other (one-to-one communication).
3. Allow users to create groups and chat with other users in the group
4. Create post - Text, image, video, link, etc
5. Like, comment, share
6. Add friends - non-directional relationship
7. See news feed
8. Notification about likes, comments, shares, friend request
9. Users must have the ability to search the platform for other users, groups, and pages.

### Non-Functional requirements
1. Read heavy - read to write ratio is very high
2. Compatibility: The system should be compatible with a wide range of devices and browsers.
3. Internationalization: The system should be able to support a wide range of languages and cultural norms.
4. Scalability: refers to the system’s ability to handle a large number of users and a high volume of traffic without deteriorating performance.
5. Reliability: The system should always be available and operating.
6. Security: The system must protect user data and privacy while preventing unauthorised access or tampering.

## Estimation
### Cheat sheet:
- Character = 1 byte
- Metadata = 5-10 kb
- 1080p image = 2 MB
- 1080p video (1 min) = 30 MB

### Fact:
- 1B daily active user (DAU)
- 5B views per day
- 10M posts per day

### Estimates:
- Read-write ratio: 
  - 5B / 10M = 500:1
- RPS:
  - Read: 5B / (24 * 60 * 60) = 58k rps
  - Write: 10M / (24 * 60 * 60) = 115 rps
- Posts (text) DB storage:
  - 10 kb * 10M = 100 GB (daily)
  - 30 * 100 GB = 3 TB (monthly)
- Posts (image) DB storage:
  - 2 MB * 10M = 19 TB (daily)
  - 30 * 19 TB = 570 TB (monthly)
- Posts (video) DB storage:
  - 30 MB * 10M = 286 TB (daily)
  - 30 * 286 TB = 8580 TB (monthly)


## Components
### Microservices:
**For educational purposes, all services are combined into one**
1. **Post service:** _Handles the creation, retrieval, update, and deletion of posts made by users. It should also handle post interactions like likes, comments, and shares._
2. **Notification service:** _Responsible for sending notifications to users about relevant activities such as likes, comments, mentions, and new followers._
3. **Search service:** _Enables users to search for other users, posts, hashtags, or locations._
4. **Media storage service**
5. **Direct Messaging Service:** _Manages private messaging between users._
6. **Feed Service:** _Aggregates and generates user-specific feeds based on the people they follow and their own posts._

### Third-party:
1. Kafka. TODO
2. Postgresql. TODO
3. Elasticsearch. TODO
4. Keycloak. TODO

