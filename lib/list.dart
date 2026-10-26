
import 'package:flutter/material.dart';

void main() {
  runApp(MaterialApp(
    home: StudentListScreen(),
    debugShowCheckedModeBanner: false,
  ));
}

class StudentListScreen extends StatelessWidget {
  StudentListScreen({super.key});

  final List<String> students = [
    'Saloni',
    'Sanskruti',
    'Bhargavi',
    'Sneha',
    'Neha'
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          'Student List',
          style: TextStyle(
            fontSize: 24,
            fontWeight: FontWeight.bold,
          ),
        ),
      ),
      body: ListView.builder(
        itemCount: students.length,
        itemBuilder: (context, index) {
          return ListTile(
            leading: const Icon(
              Icons.person,
              size: 28,
            ),
            title: Text(
              students[index],
              style: const TextStyle(
                fontSize: 20,
                fontWeight: FontWeight.bold,
              ),
            ),
            subtitle: Text(
              'Student ${index + 1}',
              style: const TextStyle(
                fontSize: 16,
                fontWeight: FontWeight.bold,
              ),
            ),
          );
        },
      ),
    );
  }
}
