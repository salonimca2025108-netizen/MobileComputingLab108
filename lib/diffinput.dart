import 'package:flutter/material.dart';

void main() => runApp(MaterialApp(
  debugShowCheckedModeBanner: false,
  home: StudentFormScreen(),
));

class StudentFormScreen extends StatefulWidget {
  @override
  State<StudentFormScreen> createState() => _StudentFormScreenState();
}

class _StudentFormScreenState extends State<StudentFormScreen> {
  final nameController = TextEditingController();
  bool isInterested = false, notifications = true;
  String selectedCourse = 'MCA';

  void showStudentDetails() {
    if (nameController.text.trim().isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Please enter your name')),
      );
      return;
    }
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: Text('Student Details',
            style: TextStyle(fontWeight: FontWeight.bold,
                color: Colors.deepPurple)),
        content: Text(
          'Name: ${nameController.text}\nCourse: $selectedCourse\n'
              'Interested: ${isInterested ? "Yes" : "No"}\n'
              'Notifications: ${notifications ? "On" : "Off"}',
          style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
        ),
        actions: [
          TextButton(
            onPressed: () => Navigator.pop(context),
            child: Text('OK', style: TextStyle(color: Colors.purple)),
          ),
        ],
      ),
    );
  }

  @override
  void dispose() {
    nameController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.purple.shade50,
      appBar: AppBar(
        title: Text('Student Registration',
            style: TextStyle(fontWeight: FontWeight.bold,
                color: Colors.white)),
        backgroundColor: Colors.deepPurple,
      ),
      body: SingleChildScrollView(
        padding: EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text('Enter Student Name',
                style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold,
                    color: Colors.deepPurple)),
            SizedBox(height: 8),
            TextField(
              controller: nameController,
              decoration: InputDecoration(
                hintText: 'Enter your name',
                filled: true,
                fillColor: Colors.white,
                border: OutlineInputBorder(),
              ),
            ),
            SizedBox(height: 20),
            Text('Select Course',
                style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold,
                    color: Colors.deepPurple)),
            DropdownButton<String>(
              value: selectedCourse,
              isExpanded: true,
              items: ['MCA', 'MBA', 'BCA', 'BBA'].map((course) =>
                  DropdownMenuItem(value: course, child: Text(course,
                      style: TextStyle(fontWeight: FontWeight.bold)))
              ).toList(),
              onChanged: (value) =>
                  setState(() => selectedCourse = value!),
            ),
            CheckboxListTile(
              title: Text('Interested in Programming',
                  style: TextStyle(fontWeight: FontWeight.bold)),
              value: isInterested,
              activeColor: Colors.deepPurple,
              contentPadding: EdgeInsets.zero,
              controlAffinity: ListTileControlAffinity.leading,
              onChanged: (value) => setState(() => isInterested = value!),
            ),
            SwitchListTile(
              title: Text('Enable Notifications',
                  style: TextStyle(fontWeight: FontWeight.bold)),
              value: notifications,
              activeTrackColor: Colors.green,
              contentPadding: EdgeInsets.zero,
              onChanged: (value) => setState(() => notifications = value),
            ),
            SizedBox(height: 16),
            ElevatedButton(
              style: ElevatedButton.styleFrom(
                backgroundColor: Colors.deepPurple,
                foregroundColor: Colors.white,
                minimumSize: Size(double.infinity, 48),
              ),
              onPressed: showStudentDetails,
              child: Text('SUBMIT',
                  style: TextStyle(fontSize: 18,
                      fontWeight: FontWeight.bold)),
            ),
            Center(
              child: TextButton(
                onPressed: () => setState(() {
                  nameController.clear();
                  selectedCourse = 'MCA';
                  isInterested = false;
                  notifications = true;
                }),
                child: Text('RESET',
                    style: TextStyle(fontSize: 16,
                        fontWeight: FontWeight.bold, color: Colors.red)),
              ),
            ),
          ],
        ),
      ),
    );
  }
}