const express = require("express")
const mongoose=require("mongoose")
mongoose.connect("mongoDBuri/HMS")// url of mongoDb atlas database
//if connection is successfull
.then(()=>{
console.log("mongodb connected");
})
// if connection fails
.catch(()=>{
console.log('failed');
})
const cors = require("cors")
const app = express()
app.use(express.json())
app.use(express.urlencoded({ extended: true }))
app.use(cors())
app.get("/",cors(),(req,res)=>{
})

const PORT=3000;

const StudentData = new mongoose.Schema(
{
    rollNo:{type:String,require:true},
    name:{type:String,require:true},
    email:{type:String,require:true},
    phone:{type:String,require:true},
    address:{type:String,require:true},
    aadhar:{type:String,require:true},
    password:{type:String,require:true},
    course:{type:String,require:true},
    branch:{type:String},
    batch:{type:String,require:true},
    room:String,
    status:String
});
const Student = mongoose.model("Student",StudentData);

const ComplaintData = new mongoose.Schema(
  {
      email:{type:String,require:true},
      complaint:{type:String,require:true},
      roomNo:{type:String,require:true},
      status:{type:String,require:true}
  });
  const Complaint = mongoose.model("Complaint",ComplaintData);

const WardenData = new mongoose.Schema(
  {
      
      email:{type:String,require:true},
      password:{type:String,require:true},
      status:{type:String,require:true}
  });
  const Warden = mongoose.model("Warden",WardenData);

const LeaveData = new mongoose.Schema(
    {  //rollNo, name,course,branch,batch,room,days,fromDate,toDate,reason;
        rollNo:{type:String,require:true},
        name:{type:String,require:true},
        course:{type:String,require:true},
        branch:{type:String,require:true},
        batch:{type:String,require:true},
        room:{type:String,require:true},
        days:{type:String,require:true},
        fromDate:{type:String,require:true},
        toDate:{type:String,require:true},
        reason:{type:String,require:true},
        status:{type:String,require:true}
    });
const Leave = mongoose.model("Leave",LeaveData);

const FineData = new mongoose.Schema(
  {  //rollNo, name,course,branch,batch,room,days,fromDate,toDate,reason;
      rollNo:{type:String,require:true},
      name:{type:String,require:true},
      course:{type:String,require:true},
      branch:{type:String,require:true},
      batch:{type:String,require:true},
      amount:{type:String,require:true},
      reason:{type:String,require:true},
      date:{type:Date,require:true}
      
  });
const Fine = mongoose.model("Fine",FineData);

app.post("/addStudent",async(req,res)=>{
    const data=new Student({
        rollNo:req.body.rollNo,
        name:req.body.name,
        email:req.body.email,
        phone:req.body.phone,
        address:req.body.address,
        aadhar:req.body.aadhar,
        password:req.body.password,
        course:req.body.course,
        branch:req.body.branch,
        batch:req.body.batch,
        status:"offline"
    });

    // const data=new Student({
    //     rollNo:"2200140140024",
    //     name:"Shaurabh",
    //     email:"shaurabhtiwari20@gmail.com",
    //     phone:"7827191414",
    //     address:"SRMS CET Bareilly",
    //     aadhar:"987654321123",
    //     password:"1234",
    //     course:"MCA",
    //     branch:"-",
    //     batch:"2022"
    try{
        
        const result= await Student.findOne({email:data.email}).exec();
            if(result)
            {
                res.json("Student already exist")
            }
            else{
                await data.save();
                res.json("Data saved")
            }
        }
    catch(err)
    {
        console.log(err);
        res.status(500).send();
    }
});

app.post("/imposeFine",async(req,res)=>{
  const data=new Fine({
      rollNo:req.body.rollNo,
      name:req.body.name,
      course:req.body.course,
      branch:req.body.branch,
      batch:req.body.batch,
      amount:req.body.amount,
      reason:req.body.reason,
      date:new Date()
  });
  try{
    await data.save();
    console.log("fine imposed");
    res.json("Fine imposed");
  }
  catch(err)
  {
      console.log(err);
      res.status(500).send();
  }
});

app.post("/addWarden",async(req,res)=>{
 const data=new Warden({
     email:req.body.email,
     password:req.body.password,
     status:"offline"
 });
 try{
     
     const result= await Warden.findOne({email:data.email}).exec();
         if(result)
         {
             res.json("Warden already exist")
         }
         else{
             await data.save();
             res.json("Data Saved")
         }
     }
 catch(err)
 {
     console.log(err);
     res.status(500).send();
 }
});

app.post("/applyLeave",async(req,res)=>{
  console.log("inside leave",req.body.rollNo);
   const data=new Leave({
        rollNo:req.body.rollNo,
        name:req.body.name,
        course:req.body.course,
        branch:req.body.branch,
        batch:req.body.batch,
        room:req.body.room,
        days:req.body.days,
        fromDate:req.body.fromDate,
        toDate:req.body.toDate,
        reason:req.body.reason,
        status:"Unapproved"
    });

  //  const data=new Leave({
  //      rollNo:"2200140140024",
  //      name:"Shaurabh",
  //      course:"MCA",
  //      branch:"-",
  //      batch:"2022",
  //      room:"F-92",
  //      days:"3",
  //      fromDate:"26 May 2024",
  //      toDate:"29 May 2024",
  //      reason:"Visiting home",
  //      status:"Unapproved"
  //  });
   try{
       
       const result= await Leave.findOne({rollNo:data.rollNo}).exec();
           if(result)
           {
               res.json("applied")
           }
           else{
               await data.save();
               res.json("cannot apply")
           }
       }
   catch(err)
   {
       console.log(err);
       res.status(500).send();
   }
});

app.get("/getLeaves",async(req,res)=>{
  try{
  const result = await Leave.find();
  if(result)
    {
      console.log("Leave Result", result);
      res.json(result);
    }
  }

catch(err)
{
  console.log(err);
  res.status(500).send();
}
})

app.post("/getLeaveStatus",async(req,res)=>{
  const rollNo= req.body.rollNo;
  console.log(rollNo);
  try{
  const result = await Leave.find({rollNo:rollNo});
  if(result)
    {
      console.log("Leave Result", result);
      res.json(result);
    }
  }

catch(err)
{
  console.log(err);
  res.status(500).send();
}
})

app.post("/getStudentDetail",async(req,res)=>{
  const rollNo= req.body.rollNo;
  try{
  const result = await Student.findOne({rollNo:rollNo});
  if(result)
    {
      console.log("Student Result", result);
      res.json(result);
    }
  }

catch(err)
{
  console.log(err);
  res.status(500).send();
}
})

app.get("/getComplaints",async(req,res)=>{
  try{
  const result = await Complaint.find();
  if(result)
    {
      console.log("Complaint Result", result);
      res.json(result);
    }
  }

catch(err)
{
  console.log(err);
  res.status(500).send();
}
})

app.post("/getLeaveDetails",async(req,res)=>{

  const id=req.body.id;
  try{
  const result = await Leave.findOne({_id:id});
  if(result)
    {
      console.log("Leave Result", result);
      res.json(result);
    }
  }

catch(err)
{
  console.log(err);
  res.status(500).send();
}
})

app.post("/getComplaintDetails",async(req,res)=>{
  const id=req.body.id;
  try{
  const result = await Complaint.findOne({_id:id});
  if(result)
    {
      console.log("Complaint Result", result);
      res.json(result);
    }
  }
catch(err)
{
  console.log(err);
  res.status(500).send();
}
})

app.post("/updateLeaveStatus",async(req,res)=>{
   console.log(req.body.id);
   const id=req.body.id;
   const status=req.body.status;
   try {
    const result = await Leave.updateOne({_id:id}, { $set: {status: status} });
    console.log("Leave Update result:", result);

    if (result && result.modifiedCount > 0) {
      console.log("Leave updated successfully");
      res.json("updated")
    } else {
      console.log("Update failed");
      res.json("update failed")
    }
  } catch (err) {
    console.error("Error updating user:", err);
  }
});

app.post("/loginStudent",async(req,res)=>{
    
    console.log(req.body.email);
    const data={
        email:req.body.email,
        password:req.body.password
    }
    try{
    const result = await Student.updateOne(data, { $set: {status:"true"} });
            if (result && result.modifiedCount > 0) {
              console.log("Logged In successfully");
              res.json("Login Successful")
            } else {
              console.log("login failed");
              res.json("login failed")
            }
    }
catch(err)
{
    console.log(err);
    res.status(500).send();
}
})

app.post("/loginWarden",async(req,res)=>{
  try{
  console.log(req.body.email);
  const data={
      email:req.body.email,
      password:req.body.password
  }
  const result = await Warden.findOne(data).exec();
      if(result)
      {
         try{
          const result = await Warden.updateOne({email:data.email}, { $set: {status:"true"} });
          console.log("Login Result", result);
      
          if (result && result.modifiedCount > 0) {
            console.log("Logged In successfully");
            res.json("Login Successfull")
          } else {
            console.log("login failed");
            res.json("login failed")
          }
         }
         catch(err)
         {
           console.log(err);
         }
      }else
      {
          res.status(404).send()
      }
  }
catch(err)
{
  console.log(err);
  res.status(500).send();
}
})

app.post("/logOutStudent",async(req,res)=>{
    const data={
        rollNo:req.body.rollNo,
        status:"false"
    }
  try {
    const result = await Student.updateOne({rollNo:data.rollNo}, { $set: data });
    console.log("Logged Out result:", result);

    if (result && result.modifiedCount > 0) {
      console.log("Logged Out successfully");
      res.json("Logged Out")
    } else {
      console.log("LogOut failed");
      res.json("logOut failed")
    }
  } catch (err) {
    console.error("Error logging out:", err);
  }
});

app.post("/logOutWarden",async(req,res)=>{
  const data={
      email:req.body.email,
      status:"false"
  }
try {
  const result = await Warden.updateOne({email:data.email}, { $set: data });
  console.log("Logged Out result:", result);

  if (result && result.modifiedCount > 0) {
    console.log("Logged Out successfully");
    res.json("Logged Out")
  } else {
    console.log("LogOut failed");
    res.json("logOut failed")
  }
} catch (err) {
  console.error("Error logging out:", err);
}
});

app.post("/raiseComplaint",async(req,res)=>{
  try{
  console.log(req.body.email);
  const data=new Complaint({
    email:req.body.email,
    complaint:req.body.complaint,
    roomNo:req.body.roomNo,
    status:"Unresolved"
});
  const result = await Student.findOne({email:data.email});
      if(result)
      {
         await data.save();
         console.log("Complaint Registered");
      }
  }
catch(err)
{
  console.log(err);
  res.status(500).send();
}
})

app.post("/removeStudent",async(req,res)=>{
    const rollNo = req.body.rollNo;
    console.log("Deleting ",rollNo);
    try {
        const result = await Student.deleteOne({ rollNo: rollNo });
    
        if (result.deletedCount > 0) {
          res.status(200).json({ message: 'Student deleted successfully' });
        } else {
          res.status(404).json({ message: 'Student not found' });
        }
      } catch (error) {
        res.status(500).json({ message: 'Internal server error', error: error.message });
      }
})

app.post("/updateStudent",async(req,res)=>{
    const data={
        rollNo:req.body.rollNo,
        name:req.body.name,
        email:req.body.email,
        phone:req.body.phone,
        address:req.body.address,
        aadhar:req.body.aadhar,
        password:req.body.password,
        course:req.body.course,
        branch:req.body.branch,
        year:req.body.year,
        room:req.body.room
    }
  try {
    const result = await Student.updateOne({rollNo:data.rollNo}, { $set: data });
    console.log("Update result:", result);

    if (result && result.modifiedCount > 0) {
      console.log("Student updated successfully");
      res.json("updated")
    } else {
      console.log("Update failed");
      res.json("update failed")
    }
  } catch (err) {
    console.error("Error updating user:", err);
  }
});

app.post("/allotRoom",async(req,res)=>{
    const data={
        rollNo:req.body.rollNo,
        room:req.body.room
    }
  try {
    const result = await Student.find({room:data.room});
    if(result)
      {
        res.status(200).json("Room is already Allotted");
      }
    else{
    const result = await Student.updateOne({rollNo:data.rollNo}, { $set: {room:data.room} });
    console.log("Update result:", result);

    if (result && result.modifiedCount > 0) {
      console.log("Room Alotted");
      res.json("Room alloted")
    } else {
      console.log("Update failed");
      res.json("update failed")
    }
  }
  } catch (err) {
    console.error("Error updating user:", err);
  }
});

app.get("/showFineWarden",async(req,res)=>
{
   try
   {
     const result = await Fine.find();
     if(result)
     {
        console.log("Fines: ",result);
        res.json(result);
     }
   }
   catch(err)
   {
     console.log(err);
     res.json(err);
   }
})

app.post("/showFineStudent",async(req,res)=>
{
  const rollNo = req.body.rollNo;
   try
   {
     const result = await Fine.find({rollNo:rollNo});
     if(result)
     {
        console.log("Fines Student: ",result);
        res.json(result);
     }
   }
   catch(err)
   {
     console.log(err);
     res.json(err);
   }
})

app.listen(PORT,()=>{
    console.log(`${PORT}`);
});
