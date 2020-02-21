import 'package:flutter/material.dart';
import 'package:menu_ufrpe/widgets/weekdays_pager.dart';

import '../singleton.dart';

class Home extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => _HomeState();
}

class _HomeState extends State<Home> with TickerProviderStateMixin {
  final List<String> _tabsNames = ['Almoço', 'Jantar'];
  Map<String, int> _innerPagerTabIndexes;

  TabController _tabController;

  RUMenuApp app = RUMenuApp();

  int up = 0;

  void onTabChange(String tabName, int tabIndex) {
    setState(() {
      _innerPagerTabIndexes[tabName] = tabIndex;
    });
    print(_innerPagerTabIndexes);
  }

  @override
  void initState() {
    super.initState();
    _innerPagerTabIndexes = Map.fromIterables(_tabsNames, [0, 0]);
    print(_innerPagerTabIndexes);
    _tabController = TabController(vsync: this, length: _tabsNames.length);
  }

  @override
  void dispose() {
    _tabController.removeListener(_onTabChanged);
    super.dispose();
  }

  void _onTabChanged() {
    //TODO
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(app.appTitle),
        actions: <Widget>[
          IconButton(
              icon: const Icon(Icons.update),
              tooltip: 'Update Menu',
              onPressed: () {
                app.fetchJson();
                setState(() {
                  up++;
                });
              })
        ],
        bottom: TabBar(
            controller: _tabController,
            tabs: _tabsNames.map((String tabName) {
              return Tab(text: tabName);
            }).toList()),
      ),
      body: TabBarView(
          controller: _tabController,
          children: _tabsNames.map((String tabName) {
            return InnerPager(
                _innerPagerTabIndexes[tabName], tabName, onTabChange);
          }).toList()),
    );
  }
}
