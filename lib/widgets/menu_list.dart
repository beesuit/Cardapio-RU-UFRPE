import 'package:flutter/material.dart';

import '../singleton.dart';

class MenuList extends StatefulWidget {
  final String weekDay;
  final String meal;

  const MenuList(this.weekDay, this.meal);

  @override
  _MenuListState createState() => _MenuListState();
}

class _MenuListState extends State<MenuList> {
  Future<Map<String, String>> _menuData;

  RUMenuApp app = RUMenuApp();
  int a = 0;

  @override
  void initState() {
    super.initState();
    print(widget.weekDay);
    _menuData = Future<Map<String, String>>.delayed(
        Duration(seconds: 1), () => app.getMenu(widget.meal, widget.weekDay));
    print(_menuData);
  }

  @override
  Widget build(BuildContext context) {
    print('BUILD MENU LIST');
    return FutureBuilder(
      future: _menuData,
      builder:
          (BuildContext context, AsyncSnapshot<Map<String, String>> snapshot) {
        if (snapshot.hasData) {
          return ListView.builder(
              itemCount: snapshot.data.keys.length,
              itemBuilder: (context, i) {
                MapEntry<String, String> entry =
                    snapshot.data.entries.elementAt(i);
                return ListTile(
                    title: Text(entry.key), subtitle: Text(entry.value));
                //ListItem(entry.key, entry.value);
              });
        } else if (snapshot.hasError) {
          return Text('No DATA');
        } else {
          return Column(children: <Widget>[
            SizedBox(
              child: CircularProgressIndicator(),
              width: 60,
              height: 60,
            ),
            const Padding(
              padding: EdgeInsets.only(top: 16),
              child: Text('Loading...'),
            )
          ]);
        }
      },
    );
  }
}

class ListItem extends StatelessWidget {
  final String heading;
  final String content;

  ListItem(this.heading, this.content);

  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[Text(heading), Text(content)],
    );
  }
}
