import React, { useState, useEffect } from 'react';
import { ScrollView, View, Text, Image, StyleSheet, TouchableOpacity, ActivityIndicator } from 'react-native-web';

const CourseList = () => {
    const [courses, setCourses] = useState([]);
    const [weather, setWeather] = useState({});
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        fetch('http://localhost:8081/clients/courses')
            .then(response => response.json())
            .then(data => {
                setCourses(data);
                setIsLoading(false);
            })
            .catch(error => {
                console.error(error);
                setIsLoading(false);
            });
        fetch('http://localhost:8081/weather')
            .then(response => response.json())
            .then(data => {
                setWeather(data.main);
            })
            .catch(error => {
                console.error(error);
            });
    }, []);


    if (isLoading) {
        return (
            <View style={styles.loadingContainer}>
                <ActivityIndicator size="large" />
            </View>
        );
    }

    return (
        <View>
            <View style={styles.headerContainer}>
                <Text style={styles.headerText}>Temperature: {weather.temp}</Text>
                <Text style={styles.headerText}>Feels like: {weather.feels_like}</Text>
                <Text style={styles.headerText}>Minimum temperature: {weather.temp_min}</Text>
                <Text style={styles.headerText}>Maximum temperature: {weather.temp_max}</Text>
                <Text style={styles.headerText}>Pressure: {weather.pressure}</Text>
                <Text style={styles.headerText}>Humidity: {weather.humidity}</Text>
            </View>
            <ScrollView>
                {courses.map(course => (
                    <TouchableOpacity key={course.id} style={styles.cardContainer}>
                        <Image source={{ uri: course.imageUrl }} style={styles.courseImage} />
                        <View style={styles.courseInfo}>
                            <Text style={styles.courseName}>{course.name}</Text>
                            <Text style={styles.courseDescription}>{course.description}</Text>
                            <Text style={styles.coursePhone}>{course.phone}</Text>
                        </View>
                    </TouchableOpacity>
                ))}
            </ScrollView>
        </View>
    );
};

const styles = StyleSheet.create({
    cardContainer: {
        flexDirection: 'row',
        margin: 10,
        borderRadius: 10,
        backgroundColor: '#ffffff',
        elevation: 5,
        borderColor: 'black',
        borderWidth: 1
    },
    courseImage: {
        width: 100,
        height: 100,
        borderTopLeftRadius: 10,
        borderBottomLeftRadius: 10,
    },
    courseInfo: {
        flex: 1,
        padding: 10,
    },
    courseName: {
        fontWeight: 'bold',
        fontSize: 20,
    },
    courseDescription: {
        fontSize: 16,
    },
    coursePhone: {
        fontSize: 16,
    },
});

export default CourseList;
