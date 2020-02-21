import 'dart:convert';

import 'util.dart';

class RUMenuAppState{
  String appTitle = 'Menu UFRPE Singleton';
  String get title => appTitle;
  Map<String, Map<String, String>> appData;

  Future<Map<String, String>> getMenu(String meal, String weekDay) async {
    if (appData == null) {
      appData = await _loadJson();
    }
    return appData[_parseDataRequest(meal, weekDay)];
  }

  void fetchJson() async {
    Util.fetchJson();
  }

  Future<Map<String, Map<String, String>>> _loadJson() async {
    String jsonData = await Util.loadJson();
    var data = json.decode(jsonData);

    List<String> lunchNames = data.remove('almocoNomes').cast<String>();
    List<String> dinnerNames = data.remove('jantarNomes').cast<String>();

    Map<String, Map<String, String>> refactoredData = {};
    data.forEach((key, value) {
      List<String> auxList = data[key].cast<String>();
      if (key.contains('almoco')) { 
        refactoredData[key] = Map.fromIterables(lunchNames, auxList);
      } else if (key.contains('jantar')) {
        refactoredData[key] = Map.fromIterables(dinnerNames, auxList);
      }
    });

    return refactoredData;
  }

  String _parseDataRequest(String meal, String weekDay) {
    meal = meal.replaceAll('ç', 'c').toLowerCase();
    weekDay = weekDay.replaceAll('ç', 'c');
    return '$meal$weekDay';
  }

}

class RUMenuApp extends RUMenuAppState {
  static final RUMenuApp _singleton = RUMenuApp._internal();

  factory RUMenuApp() {
    return _singleton;
  }

  RUMenuApp._internal() {
    print('Singleton Initialized');
  }
}
