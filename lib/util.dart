import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';

class Util {
  static final String valueKey = 'json';

  static Future<void> fetchJson() async {
    String dataURL =
        "https://script.google.com/macros/s/AKfycbzrQ9vx_alQ5yEvSFx4uMOURVNeJPKimn30UTp0PNYWIPA6_mQ/exec";
    http.Response response = await http.get(dataURL);
    _storeJson(response.body);
    var a = json.decode(response.body);
    print(a);
    
  }

  static Future<String> loadJson() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    if (prefs.containsKey(valueKey)){
      print('TRUE');
      String string = prefs.getString(valueKey);
      return string;
    } else {
    throw Exception('No DATA');
    }
  }

  static void _storeJson(String value) async {
    SharedPreferences.setMockInitialValues({});
    SharedPreferences prefs = await SharedPreferences.getInstance();
    await prefs.setString(valueKey, value);
  }
}