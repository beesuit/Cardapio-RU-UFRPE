import 'package:flutter/material.dart';
import 'package:menu_ufrpe/widgets/home.dart';

void main() {
  runApp(App());
}

class App extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(home: Home());
  }
}

class ColoredTabBar extends Container implements PreferredSizeWidget {
  final Color color;
  final TabBar tabBar;

  ColoredTabBar({this.tabBar, this.color});

  @override
  Size get preferredSize => tabBar.preferredSize;
  
  Widget build(BuildContext context) {
    return Container(
      color: color,
      child: Material(
        child: tabBar,
        type: MaterialType.transparency,
      ) 
    );
  }
}
