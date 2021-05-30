# PROJET MOBILE - 3A UFA - WANG Yannick
[![CircleCI](https://circleci.com/gh/circleci/circleci-docs.svg?style=svg)](https://circleci.com/gh/circleci/circleci-docs)

### About the project
Simple project done in Kotlin with coinpaprika's API, showing all the cryptocurrencies available in a RecyclerView then onClick on a item shows additional informations about the cryptocurrency.

### Prerequisites
-   Install Android Studio  
-   Checkout Project
```
git clone https://github.com/wangyannick/cours.git
```

##  Done on the application
- One recyclerview with all info of cryptocurrency.
- One detail page with cryptocurrency more info and detailed graph.
- Call coinpaprika's API to get all the inforrmations. (https://coinpaprika.com/)
- Stocking in cache previous API calls.
- Added notification, can send notification to all devices with Firebase Cloud Messaging.
- Two CI/CD : CircleCI and one CI from Github.
- Added one Singleton and one MVVM.
