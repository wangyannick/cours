# PROJET MOBILE - 3A UFA - WANG Yannick
[![CI](https://github.com/wangyannick/projet-mobile/actions/workflows/blank.yml/badge.svg)](https://github.com/wangyannick/projet-mobile/actions/workflows/blank.yml)
[![CircleCI](https://circleci.com/gh/circleci/circleci-docs.svg?style=svg)](https://circleci.com/gh/circleci/circleci-docs)

### About the project
Simple project done in Kotlin with coinpaprika's API, showing all the cryptocurrencies available in a RecyclerView then onClick on a item shows additional informations about the cryptocurrency.

### Prerequisites
-   Install Android Studio  
-   Checkout Project
```
git clone https://github.com/wangyannick/projet-mobile.git
```

##  Done in the application
- One recyclerview with all info of cryptocurrency.
- One detail page with cryptocurrency more info and detailed graph.
- Call coinpaprika's API to get all the inforrmations. (https://coinpaprika.com/)
- Stocking in cache previous API calls.
- Added notification, can send notification to all devices with Firebase Cloud Messaging.
- Two CI/CD : CircleCI and one CI from Github.
- Added one Singleton and one MVVM.

## Screenshot and GIF
<img src="https://user-images.githubusercontent.com/49391108/120119169-837a0800-c196-11eb-8fc0-dcd526d5420c.gif" alt="Application GIF" width="auto" height="500"> <img src="https://user-images.githubusercontent.com/49391108/120119105-2a11d900-c196-11eb-914e-ee7a1c49ab1d.jpg" alt="RecyclerView" width="auto" height="500"> <img src="https://user-images.githubusercontent.com/49391108/120119112-3138e700-c196-11eb-92c6-3a8c51e6e891.jpg" alt="Detail" width="auto" height="500"> <img src="https://user-images.githubusercontent.com/49391108/120112755-68e46680-c177-11eb-8c76-ebcdcc92debd.jpg" alt="Firebase Notification" width="auto" height="500">

