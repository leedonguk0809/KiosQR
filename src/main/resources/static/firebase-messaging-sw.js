importScripts('https://www.gstatic.com/firebasejs/8.10.1/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/8.10.1/firebase-messaging.js');



// Initialize the Firebase app in the service worker by passing in
// your app's Firebase config object.
// https://firebase.google.com/docs/web/setup#config-object
firebase.initializeApp({
    apiKey: 'AIzaSyDxyPpuTP0BqOi5yUUpM8Z52al-eGO0zI0',
    authDomain: 'f2024-2fcad.firebaseapp.com',
    databaseURL: 'https://f2024-2fcad.firebaseio.com',
    projectId: 'f2024-2fcad',
    storageBucket: 'f2024-2fcad.appspot.com',
    messagingSenderId: '579259842838',
    appId: '1:579259842838:web:5c18618ba2033e2a961037',
    measurementId: 'G-FKC7TYJBJD',
});

// Retrieve an instance of Firebase Messaging so that it can handle background
// messages.
const messaging = firebase.messaging();


messaging.onBackgroundMessage((payload) => {
    console.log(
        '[firebase-messaging-sw.js] Received background message ',
        payload
    );

    // Customize notification here
    const notificationTitle = 'Background Message Title';
    const notificationOptions = {
        body: 'Background Message body.',
        icon: '/firebase-logo.png'
    };

    self.registration.showNotification(notificationTitle,notificationOptions);
});