
import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

void main() => runApp(MaterialApp(
  debugShowCheckedModeBanner: false,
  home: UserScreen(),
));

class UserScreen extends StatefulWidget {
  @override
  State<UserScreen> createState() => _UserScreenState();
}

class _UserScreenState extends State<UserScreen> {
  List users = [];
  bool loading = true;

  Future<void> fetchUsers() async {
    try {
      final response = await http.get(Uri.parse(
          'https://jsonplaceholder.typicode.com/users'));

      if (response.statusCode == 200) {
        List data = jsonDecode(response.body);
        List names = [
          'Saloni', 'Priya', 'Rahul', 'Amit', 'Sneha',
          'Neha', 'Rohan', 'Pooja', 'Anjali', 'Karan'
        ];

        setState(() {
          users = data.map((user) => {
            'id': user['id'],
            'name': names[user['id'] - 1],
            'email': user['email'],
          }).toList();
          loading = false;
        });
      } else {
        setState(() => loading = false);
      }
    } catch (e) {
      setState(() => loading = false);
    }
  }

  @override
  void initState() {
    super.initState();
    fetchUsers();
  }

  @override
  Widget build(BuildContext context) => Scaffold(
    backgroundColor: Colors.purple.shade50,
    appBar: AppBar(
      title: Text('REST API - Student Details',
          style: TextStyle(fontWeight: FontWeight.bold,
              color: Colors.white)),
      backgroundColor: Colors.deepPurple,
      actions: [
        IconButton(
          icon: Icon(Icons.refresh, color: Colors.white),
          onPressed: () {
            setState(() => loading = true);
            fetchUsers();
          },
        ),
      ],
    ),
    body: loading
        ? Center(child: CircularProgressIndicator(
        color: Colors.deepPurple))
        : users.isEmpty
        ? Center(child: Text(
        'Unable to fetch data. Check internet.',
        style: TextStyle(fontWeight: FontWeight.bold)))
        : ListView.builder(
      itemCount: users.length,
      itemBuilder: (context, index) {
        final user = users[index];
        return Card(
          margin: EdgeInsets.all(8),
          elevation: 4,
          color: Colors.white,
          child: ListTile(
            leading: CircleAvatar(
              backgroundColor: Colors.deepPurple,
              child: Text('${user['id']}',
                  style: TextStyle(
                      color: Colors.white,
                      fontWeight: FontWeight.bold)),
            ),
            title: Text('${user['name']}',
                style: TextStyle(
                    fontSize: 18,
                    fontWeight: FontWeight.bold,
                    color: Colors.deepPurple)),
            subtitle: Text('${user['email']}',
                style: TextStyle(fontSize: 14)),
          ),
        );
      },
    ),
  );
}
