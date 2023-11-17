import 'package:app/counter/counter.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const CounterApp());
}

class CounterApp extends MaterialApp {
  const CounterApp({super.key}) : super(home: const CounterPage());
}
