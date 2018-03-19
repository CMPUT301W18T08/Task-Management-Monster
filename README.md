# Task-Management-Monster
Project part 3 planning
For project part 3, our goal is to finish the Object-Oriented Analysis and Design of the project using UML class diagram while focusing on the most important class (as described as our focus in part 4 planning), as well as provide new task case for each use class described in the UML diagram.

For this part, we will divided the project as components, each team member is responsible for finishing the Object-Oriented Analysis and Design of the project using UML class diagram, provide note for classes and inferences that have purpose not clear, as well as provide test case for critical function for each class.

If possible, it is highly recommended to construct and provide a functional UI for corresponding class in the responsible component, it will be more helpful to create a UML diagram and provide test case. And release pressure from the part 4 programming task.

The Part 3 is divided into following component:

User information and New Request: [Login the app], [Modify profile], [Requestor: create new task]
Provider bid task: [Provider: view and bid on task]
Requestor view list of task in Mytask: [Requestor: Mytask]
Requestor view detail task in Mytask: [Requestor: Mytask view detail]
Provider view list of task in Mytask: [Provider: Mytask]
Provider view detail task in Mytask: [Provider: Mytask view detail]
optional:

Requestor views detail information of bid in one of this task and more operation on it
Requestor views separate list of requested, bided, and accepted task in my task
Provider views separate list of bided, accepted task in my task
Detail of each component and corresponding use case can be view in part 4 planning.

Part 3 Responsibility
part 1:Tianyi Liang(tianyi4)
part 2: Wenhan Yang(wyang2)
Part 3: Yuhang Xiong(yxiong4)
Part 4: Hanwen Yang(hyang4)
Part 5: Xixuan Song (xixuan)
part 6: Xiang Fan (xf4)
Project part 4 planning
For project part 4, our goal is to implement the basic application for the task requestor and task provider. The prototype should be a functional simple version of the desired end product of the project but can achieve all the necessary task the product need to preform.

The prototype should like: A user of the app could be both a task requester and a task provider. A task provider can offer their service by making a monetary bid on a task. The task requester can accept a bid, so the task is assigned to the winning task provider. The task requester can denote when the task is done.

In detail, the prototype should be a basic working android application that satisfied the user's basic need of signup and login, creating/ searching and biding a task, accepting a bid for task, view the current task in either role of requestor or provider, and done a task, view detail of each task and do the corresponding action.

The more risky and potential very time costing task that will be move to part 5 include:

keyword searching (involve database application and optimization if data too large, required more time)
Geolocation and Maps (necessary technology required further study)
Photographs (necessary technology required further study)
Offline behaviour (necessary technology required further study and testing)
Message notified to user (time costing task for managing message from different source)
Any potential complexed Interactive interface.
Changing the base setting of the application from sys Admin account.
Complexed graph and art feature.
Most of the exception and rare situation.
The project part 4 will only focus on the basic function of the project and aim to deliver a working and reliable prototype that meet the basic functional need of the customer.

(The above information may change during the meeting between the goups)

Detail component of project 4:
[Login the app]

create new account
UC 03.01.01
login with existing account
(required new use case)
[Modify profile]

View one's own user information.
(may required new use case)
Modify some information about profile
UC 03.02.01
view a page of a user's contact and other information after click his username
UC 03.03.01
[Requestor: create new task]

Create new task
US 01.01.01
(note: for provider and requestor, each should has independent use case for view 'mytask' list (currently named) as they are in separate activity for easier management of user's independent role as task requestor and task provider )

[Provider: view and bid on task]

View a list of task
UC 04.01.01 (currently not required keyword search, just view list. may required new use case)
View detail information of each task
UC 05.02.01
Bid on a chosen task
UC 05.01.01
[Requestor: Mytask]

View a list of task of the requestor

UC 01.02.01
required discussion: View a list of task of requestor that are in status bided (will discuss)

UC 05.04.01
required discussion: View a list of task of requestor that are in status accepted (will discuss)

UC 06.02.01
[Requestor: Mytask view detail]

View detail information of the requestor
UC 02.01.01
UC 02.02.01
Modify the information of an requestor's task
UC 01.03.01
Delete an requestor's task
UC 01.04.01
View a list of bid for a bided status task
UC 05.04.01
Set the status of a assigned task to done or requested (requestor change status)
UC 07.01.01
UC 07.02.01
below three required further discussion for a extra bid detail information page

View a list of bid of a requestor's requestor (will discuss)
UC 05.05.01
Decline a bid from a list of bid in detail information page of this bid (will discuss)
UC 05.07.01
accept a bid in the detail information page of a bid (will discuss)
UC 05.06.01
[Provider: Mytask]

View a list of task that provider bided / being assigned.

required discussion: View a list of task that provider has bided (will discuss)

UC 05.02.01
required discussion: View a list of task that privider has assigned (will discuss)

UC 06.01.01
[Provider: Mytask view detail]

View detail information of the provider
UC 02.01.01
UC 02.02.01
UC 05.02.01
UC 06.01.01
The below application category will not be included in the part 4: - offline behaviour - Photographs - Geolocation and Maps

Project part 5 planning
Our main purpose of part 5 will focus on following tasks:

Offline behavior 👍
Photographs of user profile 👍
Geolocation and maps 👍
Notify message 👍
Keyword search 👍
Also, in part 5, we will keep improving our code and application structure. And creating a more convenience and user-friendly UI is in our plan of this part.

Part 5 is final stage of our project. All issues and unfinished tasks will be finished in this part.

Detail component of project 5:
[Offline behavior]

See Use Case 08 Offline Behaviour

[Photographs of user profile]

See Use Case 9: Photographs

[Geolocation and maps]

See Use case 10: Geolocation and Maps

[Notify message]

See UC 05.03.01

[Keyword search]

See Use Case 4: Searching
