
import 'package:flutter/material.dart';

void main() {
  runApp(MaterialApp(
    debugShowCheckedModeBanner: false,
    home: CheckboxExample(),
  ));
}

class CheckboxExample extends StatefulWidget {
  const CheckboxExample({super.key});

  @override
  State<CheckboxExample> createState() =>
      _CheckboxExampleState();
}

class _CheckboxExampleState extends State<CheckboxExample> {
  bool selected = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.purple.shade50,

      appBar: AppBar(
        title: const Text(
          'Checkbox Example',
          style: TextStyle(
            color: Colors.white,
            fontWeight: FontWeight.bold,
          ),
        ),
        backgroundColor: Colors.purple,
      ),

      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const Text(
              'Welcome, Saloni!',
              style: TextStyle(
                fontSize: 26,
                fontWeight: FontWeight.bold,
                color: Colors.deepPurple,
              ),
            ),

            const SizedBox(height: 20),

            CheckboxListTile(
              title: const Text(
                'I like Programming',
                style: TextStyle(
                  fontSize: 18,
                  fontWeight: FontWeight.bold,
                  color: Colors.deepPurple,
                ),
              ),
              value: selected,
              activeColor: Colors.purple,
              checkColor: Colors.white,
              onChanged: (value) {
                setState(() {
                  selected = value ?? false;
                });
              },
            ),

            const SizedBox(height: 15),

            Text(
              selected
                  ? 'Great, Saloni! Selected'
                  : 'Not Selected',
              style: TextStyle(
                fontSize: 20,
                fontWeight: FontWeight.bold,
                color: selected
                    ? Colors.green
                    : Colors.red,
              ),
            ),

            const SizedBox(height: 10),

            Icon(
              selected
                  ? Icons.check_circle
                  : Icons.radio_button_unchecked,
              size: 45,
              color: selected
                  ? Colors.green
                  : Colors.grey,
            ),
          ],
        ),
      ),
    );
  }
}
