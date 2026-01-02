# 👨‍💻 Developer Guide – WatchAppV5

This document is intended for developers who want to understand **what WatchAppV5 is trying to achieve**, how the app behaves conceptually, and how different parts of the system interact.

This is **not a line-by-line code explanation**, but a guide to the project’s intent and flow.

---

## 🧭 Project Goal (Developer Perspective)

WatchAppV5 explores **real-time, persistent communication on Wear OS**.

The key challenge addressed by this project is:
- Maintaining a live connection on a smartwatch
- Handling frequent lifecycle changes
- Ensuring the UI remains responsive and minimal

---

## 🔄 High-Level App Flow

1. The app starts and establishes a live communication channel.
2. Users can send short messages from the watch.
3. Incoming messages are received instantly and displayed.
4. The app reacts safely to lifecycle changes (screen changes, app backgrounding).

The design prioritizes **stability over feature overload**.

---

## 🧠 Design Principles

- Single source of truth for communication
- Clear separation between UI and communication logic
- Minimal UI recomposition
- Watch-friendly navigation and interaction patterns

---

## 🧪 Intended Experiments

This project is also a sandbox for experimenting with:
- Real-time data streams on Wear OS
- Connection resilience
- User experience on extremely small screens

---

## 📌 Notes for Contributors

This project is experimental and evolving.
Contributions should focus on:
- Reliability
- Simplicity
- Wear OS constraints (battery, lifecycle, screen size)

If you are modifying behavior, always consider how it affects:
- Connection stability
- User clarity
- Watch performance
