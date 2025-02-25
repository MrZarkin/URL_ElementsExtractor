# Elements Extractor

This small project was carried out as part of a technical test to enter a company. The exercise is simple: write a program in the language of your choice to retrieve temperature values from a text file and determine the minimum, maximum and average values. All for each weather station. Note that [the file](https://hwpublic.blob.core.windows.net/dataengineering/measurements.txt) is 1,000,000,000 lines long (~13GB)

The file is structured as follows:
```
Hamburg;15.0
Bulawayo;10.9
Palembang;18.8
St. John's;30.2
Krakow;21.6
...
```
The program should therefore output the minimum, average and maximum temperatures per station, sorted alphabetically.

Example of output:
{Abha=5.0/18.0/27.4, Abidjan=15.7/26.0/34.1, Abéché=12.1/29.4/35.6, Accra=14.7/26.4/33.1, Addis Ababa=2.1/16.0/24.3, Adelaide=4.1/17.3/29.7, ...}

## Requirements

- [Java 11+ (Java 8 minimum)](https://www.oracle.com/java/technologies/downloads/)

## Some explications

### HTTPLoader

The `getList(String _url)` function is used to retrieve the list of website elements given above. To do this, we first need to make an Internet request, check that everything is OK and then start retrieving each element line by line, putting them into a `HashMap` (a kind of dictionary). Thanks to this, we'll be able to analize them and determine the minimum, maximum and average temperature.

```Java
URI uri = new URI(_url);
URL url = uri.toURL();
```
Using URIs allows you to validate the URL and avoid certain syntax errors. It is then converted into a URL for subsequent queries.

```Java
HttpURLConnection connection = (HttpURLConnection) url.openConnection();
connection.setRequestMethod("GET");
```
The HttpURLConnection class sends HTTP requests and receives responses, opening an HTTP connection to the specified URL.

```java
BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
String line;

while ((line = in.readLine()) != null) 
{
    analyseData(line);
}
                
in.close();
```
To put it simply, we retrieve the HTTP response as a stream, call the `analyseData` function to analyze each line, and finally close the stream.

I was talking about the `analyseData` function:

```Java
String[] args = line.split(";");
Float number = Float.parseFloat(args[1]);
Data dt = new Data();
```

We use this to separate each line into two “arguments”: the name of the city and the temperature. 

We'll be able to check whether our dictionary already contains this city, and if not, we'll add it along with the minimum data (the temperature in argument 1 will be the minimum, maximum and total. If argument 0, the city, already exists in the dictionary, we'll simply check: if it's smaller than the current minimum, we'll change it, same for the maximum...

`setMoy` do the average temperature of each city in the HashMap, so the total / count. `setMoy` do the average temperature of each city in the HashMap, so the total / count. And finally `sortMap` will sort the entire HashMap alphabetically.

### Data

It's just a class of getters/setters. It's just a class of getters/setters. Essential for calculating minimum, maximum and average temperatures.

### Main

```Java
HTTPLoader.getList("https://hwpublic.blob.core.windows.net/dataengineering/measurements.txt");
```

Here we'll call the `getList` function of the HTTPLoader class, with the url as parameter

## Set up

In VSCode, open a terminal and clone this repository in your personal project by writing :
```HTTPS
https://github.com/MrZarkin/URL_ElementsExtractor.git
```