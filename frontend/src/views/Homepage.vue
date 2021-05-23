<template v-if="dataLoaded">
    <div >
      <!-- Table -->
      <a-table :columns="columns" :data-source="dataSource">
        <!--<a slot="name" slot-scope="text">{{text}}</a>-->
        <span slot="customTitle"><a-icon type="smile-o" /> Name</span>
        <span slot="action" slot-scope="assignment"> <!-- slot-scopre อ้างอิง object ในแต่ละ row -->
          <a @click="showEditForm(assignment)">Edit</a>
          <a-divider type="vertical" />
          <a-popconfirm v-if="dataSource.length" title="Sure to delete?" @confirm="() => deleteAssignment(assignment.id) ">
            <a>Delete</a>
          </a-popconfirm>
          <a-divider type="vertical" />
        </span>
      </a-table>

      <!--<a-button type="primary" class="button" href='/addAssignment'>Add Assignment</a-button>-->
      <a-button type="primary" class="button" @click="()=>showAddForm()">Add Assignment</a-button>

      <!-- Add Form -->
      <div v-if="display === 'showAdd'" class="modal">
        <div class="inputContainer" :model="form">
          <p class="addEditTitle">Add Assignment</p>
          <b/>
          <a-textarea placeholder="Assignment name" class="inputSize1" v-model="assignmentNameValue"/>
          <a-textarea placeholder="Assigned person" class="inputSize1" v-model="assignedPersonValue"/>
          <a-range-picker class="dateBlock" @change="onChange"/>
          <a-textarea placeholder="Description" v-model="descriptionValue" :auto-size="{ minRows: 3, maxRows: 5 }" class="inputSize2"/>
          <a-button type="primary" class="button confirmBtn" @click="addAssignment(assignmentNameValue,assignedPersonValue,descriptionValue)">Confirm</a-button>
          <a-button type="primary" class="button cancelBtn" @click="closeAddForm()">Cancel</a-button>
        </div>
      </div>

      <!-- Edit Form-->
      <div v-if="display === 'showEdit'" class="modal">
        <div class="inputContainer" :model="form">
          <p class="addEditTitle">Edit Assignment: {{editAssignmentObject.assignmentName}}</p>
          <b/>

          <a-textarea placeholder="Assigned person" :defaultValue="editAssignmentObject.assignedPerson" class="inputSize1" v-model="assignedPersonValue" @change="onChangeEditAssignedPersonTextField(assignedPersonValue)"/>
          <a-range-picker class="dateBlock" @change="onChange" @load="onLoad(editAssignmentObject)" :defaultValue="editDateMoment"/>
          <a-textarea placeholder="Description" v-model="descriptionValue" :defaultValue="editAssignmentObject.description" :auto-size="{ minRows: 3, maxRows: 5 }" class="inputSize2" @change="onChangeEditDescriptionTextField(descriptionValue)"/>
          <a-button type="primary" class="button confirmBtn" @click="editAssignment(editAssignmentObject.id,editAssignmentObject.assignmentName,editAssignedPerson,editDescription)">Confirm</a-button>
          <a-button type="primary" class="button cancelBtn" @click="closeEditForm()">Cancel</a-button>
        </div>
      </div>

  </div>
</template>

<script>
import moment from 'moment'
import axios from 'axios'
const columns = [
  {
    title: 'Assignment name',
    dataIndex: 'assignmentName',
    key: 'assignmentName',
    scopedSlots: { customRender: 'assignmentName' },
    width: 120,
  },
  {
    title: 'Start date',
    dataIndex: 'startDate',
    key: 'startDate',
    width: 100,
  },
  {
    title: 'End date',
    dataIndex: 'endDate',
    key: 'endDate',
    width: 100,
  },
  {
    title: 'Assigned person',
    dataIndex: 'assignedPerson',
    key: 'assignedPerson',
    width: 100
  },
  {
    title: 'Description',
    dataIndex: 'description',
    key: 'description',
    width: 100
  },
  {
    title: 'Action',
    key: 'action',
    scopedSlots: { customRender: 'action' },
    width: 80
  }
];

export default {
  name: "Homepage",
  data(){
    return {
      dataSource: null,
      columns,
      dataLoaded: false,
      display: "none",
      dateStr: null,
      editAssignmentObject: null,
      editDateMoment: [],
      editDateStr: [],
      editAssignedPerson: null,
      editDescription: null
    }
  },
  async mounted () {
    await axios.get("http://localhost:8081/assignments").then((response) => (
      this.dataSource = Array.from(response.data)
    ))
    this.dataLoaded = true
  },
  methods: {
    addAssignment(assignmentNameValue,assignedPersonValue,descriptionValue){
      axios.put("http://localhost:8081/assignments/add",{
        assignmentName: assignmentNameValue, 
        startDate: this.dateStr[0],
        endDate: this.dateStr[1],
        description: descriptionValue,
        assignedPerson: assignedPersonValue
      })
      console.log('saved successfully')
      window.location.href = "/"
    },
    onChange(date, dateString) {
        console.log(date, dateString);
        this.dateStr = dateString
        console.log(this.dateStr);

    },
    deleteAssignment(id){
      axios.delete("http://localhost:8081/assignments/delete/"+id)
      console.log(id)
      console.log('deleted successfully')
      window.location.href = "/"
    },
    editAssignment(id,assignmentNameValue,assignedPersonValue,descriptionValue){
      axios.put("http://localhost:8081/assignments/edit/"+id,{
        id: id,
        assignmentName: assignmentNameValue, 
        startDate: this.dateStr[0],
        endDate: this.dateStr[1],
        description: descriptionValue,
        assignedPerson: assignedPersonValue
      })
      console.log('edited successfully')
      window.location.href = "/"
    },
    showAddForm(){
      this.display = "showAdd"
    },
    closeAddForm(){
      this.display = "closeAdd"
    },
    showEditForm(assignment){
      this.display = "showEdit"
      this.editAssignmentObject = assignment
      console.log(assignment)
      this.editDateMoment.push(moment(assignment.startDate))
      this.editDateMoment.push(moment(assignment.endDate))

      this.editAssignedPerson = assignment.assignedPerson
      console.log(this.editAssignedPerson)
      this.editDescription = assignment.description
      this.dateStr = []
      this.dateStr[0] = assignment.startDate
      this.dateStr[1] = assignment.endDate
    },
    closeEditForm(){
      this.display = "closeEdit"
    },
    onChangeEditAssignedPersonTextField(assignedPerson){
      this.editAssignedPerson = assignedPerson
      console.log(this.editAssignedPerson)
    },
    onChangeEditDescriptionTextField(description){
      this.editDescription = description
      console.log(this.editDescription)
    }
  }
}
</script>

<style scoped>
.inputContainer{
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  padding: 20px;
  height: 400px;
  width: 600px;
  border-color: lightblue;
  background-color:white;
  color: #24a0ed;
  border-radius: 2%;
}
.inputSize1{
  margin: 15px;
  width: 200px;
  height: 10px;
  position: relative;
}
.inputSize2{
  margin: 15px;
  position: relative;
  height: 400px;
  width: 500px;
}
.dateBlock{
  margin:15px
}
.button{
  margin: 15px;
  position: relative;
}
.confirmBtn{
  transform: translate(0%, 0%);
}
.cancelBtn{
  transform: translate(0%, 00%);
}
.addEditTitle{
  margin: 15px;
  font-size: 30px;
}
.modal {
  display: block; 
  position: fixed; 
  z-index: 1; 
  left: 0;
  top: 0;
  width: 100%; 
  height: 100%; 
  overflow: auto; 
  background-color: rgb(0,0,0); 
  background-color: rgba(0,0,0,0.4); 
  padding-top: 60px;
}

.animate {
  -webkit-animation: animatezoom 0.6s;
  animation: animatezoom 0.6s
}
.container {
  padding: 16px;
}
</style>
