# SyedFatima_AnimeApp
I have created an anime app for a school project. This app has 4 screens; a login screen, a home screen, a list screen and a form screen.

## Login Screen ##
The first screen is a login screen that allows the user to access to the app inserting it's username and password.
Instruccions for the usage of the app:
  - Username to be able to login into the app: **admin**
  - Password to be able to login into the app: **admin**

![login](https://user-images.githubusercontent.com/72110927/139954969-169a26b9-df91-45cc-85da-2544c6198c9e.png)

## Home Screen ##
The home screen helps you go to the two main screens. It contains two button and has two icons hinting the function of the rest of the screens.
  - Add anime button -> takes you to the form screen
  - See anime list -> takes you to the list screen
  
![home_small](https://user-images.githubusercontent.com/72110927/139954660-7ab13c33-7acf-4b86-bd7d-cb5303b5c3ac.jpg)

## Form Screen ##
The form screen gives you the option to be able to add your own anime to the list. You can add an anime's name, select it's genere, and write it's ranking. It also has a star ranking to be more friendly to the user. Once you have filled all your fields with the desired values you push the add button and add the anime to the list.

![form](https://user-images.githubusercontent.com/72110927/139956192-a07877a0-02a1-42c0-9e90-4ce0b665a31d.jpg)


## List Screen ##
The list screen is basically a screen that contains a list of animes. In the future it will be able to load animes from an API and show them in here, but for now it shows you the anime you add manually via the form screen. In this version of the app it contains a default anime image and it shows you properties as the name, the genre and the ranking of an anime. It also has a button to delete all the animes you have added.

![list](https://user-images.githubusercontent.com/72110927/139955917-6d553744-ff6b-4423-830d-18ae551439f0.jpg)

## Classes used ##
 * onBindViewHolder. Afegeix el contingut als elements declarats al ViewHolder
 * getItemCount. Ens diu quants elements haurà d’iterar de la llista
 * ViewHolder. Enllaça els elements de l’item_list amb el RecyclerView

## Structure of the project ##

```Manifest:``` This file contains the configuration parameters of the project such as permissions, services and additional libraries. A manifest file also provides information to the OS and Google Play store about your app.

```Java:``` Android Studio creates a Java file with skeleton code that you can modify. It opens the file in the Code Editor. In my app I have all the java classes there, I have the login MainActivity class.

```Res:```The res file contains all non-code resources, such as XML layouts, UI strings, and bitmap images, divided into corresponding sub-directories

 > ↳ ```Drawable:```The drawable folder holds all the images for your project. In my case I have the lock images, the email images, all there.
  
  > ↳ ```Layout:```The layout folder holds all the XML layout files for your projects. Here I have the activity_main.xml
  
  > ↳ ```Mipmap:```The mipmap folder holds the icon image for your apps launcher. These should be only as large as necessary to support the devices you want to target for your app. 
  
  > ↳ ```Values:```Values folder is used to store the values for the resources that are used in many Android projects to include features of color, styles, dimensions etc.
  
  >>↳ ```Themes```
