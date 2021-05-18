<template v-if="dataLoaded">
    <div >
      <a-table :columns="columns" :data-source="dataSource">
        <a slot="name" slot-scope="text">{{ text }}</a>
          <span slot="customTitle"><a-icon type="smile-o" /> Name</span>
          <span slot="action">
          <a>Edit</a>
          <a-divider type="vertical" />
          <a>Delete</a>
          <a-divider type="vertical" />
        </span>
      </a-table>

      <a-button type="primary" class="button" href='/addAssignment'>Add Assignment</a-button>
  </div>
</template>

<script>
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

/*function addAssignment(){
  axios.put("/assignments",{assignmentName: , startDate:, endDate:, description:, assignedPerson:,}).then(
    function(response){console.log('saved successfully')
  );
}*/

/*function onChange(date, dateString) {
      console.log(date, dateString);
}*/

export default {
  name: "Homepage",
  data(){
    return {
      dataSource: null,
      columns,
      dataLoaded: false}
  },

  async mounted () {
    await axios.get("http://localhost:8082/assignments").then((response) => (
      this.dataSource = Array.from(response.data)
    ))
    console.log(this.dataSource)
    this.dataLoaded = true
  },

}
</script>

<style scoped>
.inputContainer{
  position: relative;
  margin-left: 100px;
  margin-top: 80px;
  padding: 20px;
  height: 400px;
  width: 600px;
  border-color: lightblue;
  background-color:lightcoral
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
.addEditTitle{
  margin: 15px;
  font-size: 30px;
}
</style>
