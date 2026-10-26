import 'package:flutter/material.dart';

void main() => runApp(MaterialApp(home: DropdownExample()));

class DropdownExample extends StatefulWidget {
  @override
  State<DropdownExample> createState() => _DropdownExampleState();
}

class _DropdownExampleState extends State<DropdownExample> {
  String course = 'MCA';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.purple.shade50,
      appBar: AppBar(
        title: Text('Dropdown Example',
            style: TextStyle(color: Colors.white, fontWeight: FontWeight.bold)),
        backgroundColor: Colors.deepPurple,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('Select Your Course',
                style: TextStyle(fontSize: 26, fontWeight: FontWeight.bold,
                    color: Colors.deepPurple)),
            SizedBox(height: 20),
            DropdownButton<String>(
              value: course,
              dropdownColor: Colors.white,
              style: TextStyle(fontSize: 22, fontWeight: FontWeight.bold,
                  color: Colors.purple),
              items: ['MCA', 'MBA', 'BCA', 'BBA']
                  .map((item) => DropdownMenuItem(
                value: item,
                child: Text(item),
              ))
                  .toList(),
              onChanged: (value) {
                setState(() => course = value!);
              },
            ),
            SizedBox(height: 20),
            Text('Selected Course: $course',
                style: TextStyle(fontSize: 22, fontWeight: FontWeight.bold,
                    color: Colors.green)),
            SizedBox(height: 15),
            Text('Welcome, Saloni!',
                style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold,
                    color: Colors.pink)),
          ],
        ),
      ),
    );
  }
}