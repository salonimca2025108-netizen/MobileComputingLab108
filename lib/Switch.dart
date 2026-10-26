import 'package:flutter/material.dart';

void main() => runApp(MaterialApp(home: SwitchExample()));

class SwitchExample extends StatefulWidget {
  @override
  State<SwitchExample> createState() => _SwitchExampleState();
}

class _SwitchExampleState extends State<SwitchExample> {
  bool isOn = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.purple.shade50,
      appBar: AppBar(
        title: Text('Switch Example',
            style: TextStyle(
                color: Colors.white, fontWeight: FontWeight.bold)),
        backgroundColor: Colors.deepPurple,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('Control Your Switch',
                style: TextStyle(
                    fontSize: 26,
                    fontWeight: FontWeight.bold,
                    color: Colors.deepPurple)),
            SizedBox(height: 20),
            Switch(
              value: isOn,
              activeColor: Colors.green,
              onChanged: (value) => setState(() => isOn = value),
            ),
            SizedBox(height: 15),
            Text(
              isOn ? 'Switch is ON' : 'Switch is OFF',
              style: TextStyle(
                fontSize: 24,
                fontWeight: FontWeight.bold,
                color: isOn ? Colors.green : Colors.red,
              ),
            ),
            SizedBox(height: 15),
            Text('Welcome, Saloni!',
                style: TextStyle(
                    fontSize: 22,
                    fontWeight: FontWeight.bold,
                    color: Colors.pink)),
          ],
        ),
      ),
    );
  }
}