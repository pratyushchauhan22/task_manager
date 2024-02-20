## Project Requirememnts 

task manager + notes support 

1. ability to add new tasks
   - task has status (done/ not done)
   - by default due date = today + 7 days , can be specified by user

2. ability to  change tasks
   - modify due date , done/not done status
   - delete any task

3. ability to add notes to tasks
   - view all notes within a task
   - add new to a particular task
   - delete notes for a particular task

----
##API Endpoints (REST URLs)
-GET      /tasks                   get all tasks
-GET      /tasks/{id}              get a task by id  
-DELETE   /tasks/{id}              delete task by id
-PATCH    /task/{id}               update details of a task
-POST     /tasks                   create a new task 

-GET      /tasks/{id}/notes     	show all notes of a task
-POST     /tasks/{id}/notes 		add notes to a task      
-DELETE   /tasks/{id}/notes/{nid} delete a note from a task

---- "idempotenet"

DB entities ?

1. tasks
    - id 			int, primarykey
    - name			string
    - due_date		date
    - status		boolean

2. notes
    - id			int, primarykey
    - body			string
    - task_id		foreignkey(tasks.id)

------ 


