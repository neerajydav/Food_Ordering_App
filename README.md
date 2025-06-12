# 🍽️ Food Ordering Android App

An Android-based Food Ordering App built using **Java**, **XML**, and **Firebase**. This app provides a smooth food browsing and ordering experience for customers, complete with secure authentication, real-time database handling, and a responsive UI.

## 🚀 Features

- 🔐 **Firebase Authentication**
  - Secure Sign Up / Sign In
  - Password Recovery
- 🛒 **Food Menu Browsing**
  - Predefined categories (Pizza, Burger, Fried Rice, etc.)
  - Dynamic listing of restaurant-added items under each category
- 🧾 **Cart System**
  - Add to Cart, Remove from Cart
  - View Total Price
- 📦 **Order Placement**
  - Confirm and Place Orders
- 🏠 **User Dashboard**
  - Displays menu categories and featured items
- 📱 **Modern UI**
  - Built using XML and ConstraintLayout
  - Smooth navigation and interactions

## 🛠️ Tech Stack

| Layer         | Technology     |
|--------------|----------------|
| Language      | Java           |
| UI Design     | XML (ConstraintLayout) |
| Authentication| Firebase Auth |
| Backend       | Firebase Realtime Database |
| Storage       | Firebase Storage (for item images) |
| Tools         | Android Studio, Git, Firebase Console |

## 🔐 Firebase Authentication

- Email & Password Login
- Separate user roles: **Customer** and **Restaurant** ( Not Added )
- Login state persists across app restarts
- Proper validation and error handling

## 🗂️ Folder Structure (Overview)

app/
├── java/com/yourpackage/
│ ├── activities/
│ ├── adapters/
│ ├── fragments/
│ ├── models/
│ └── utils/
├── res/
│ ├── layout/
│ ├── drawable/
│ └── values/

