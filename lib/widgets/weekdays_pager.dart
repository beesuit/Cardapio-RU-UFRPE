import 'package:flutter/material.dart';

import 'menu_list.dart';

class InnerPager extends StatefulWidget {
  final String tabName;
  final int tabIndex;
  final void Function(String, int) onTabChange;

  const InnerPager(this.tabIndex, this.tabName, this.onTabChange);

  @override
  _InnerPagerState createState() => _InnerPagerState();
}

class _InnerPagerState extends State<InnerPager>
    with TickerProviderStateMixin //, AutomaticKeepAliveClientMixin<InnerPager>
{
  int _tabIndex;
  final List<Tab> tabs = <Tab>[
    Tab(text: 'Segunda'),
    Tab(text: 'Terca'),
    Tab(text: 'Quarta'),
    Tab(text: 'Quinta'),
    Tab(text: 'Sexta'),
  ];

  TabController _tabController;
  List<Widget> plates;

  @override
  void initState() {
    super.initState();
    _tabIndex = widget.tabIndex;
    print(_tabIndex);
    _tabController = TabController(
        initialIndex: _tabIndex, vsync: this, length: tabs.length);
    _tabController.addListener(_onTabChanged);

    plates = tabs.map((Tab t) {
      return MenuList(t.text, widget.tabName);
    }).toList();
  }

  @override
  void dispose() {
    _tabController.removeListener(_onTabChanged);
    _tabController.dispose();
    super.dispose();
  }

  void _onTabChanged() {
    _tabIndex = _tabController.index;
    widget.onTabChange(widget.tabName, _tabIndex);
    print('newTAB$_tabIndex');
  }

  @override
  Widget build(BuildContext context) {
    //super.build(context);
    return Column(children: <Widget>[
      Container(
          color: Colors.blue,
          width: double.infinity,
          //Center(child: ,)
          child: Center(
            child: Material(
                color: Colors.blue,
                child: TabBar(
                    indicatorColor: Colors.white,
                    tabs: tabs,
                    controller: _tabController,
                    isScrollable: true)),
          )),
      Expanded(child: TabBarView(controller: _tabController, children: plates))
    ]);
  }
}
