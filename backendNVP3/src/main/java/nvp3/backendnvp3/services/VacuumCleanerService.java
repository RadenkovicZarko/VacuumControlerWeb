package nvp3.backendnvp3.services;

import nvp3.backendnvp3.entities.ErrorMessage;
import nvp3.backendnvp3.entities.Status;
import nvp3.backendnvp3.entities.VacuumCleaner;
import nvp3.backendnvp3.repositories.ErrorMessageRepository;
import nvp3.backendnvp3.repositories.VacuumCleanerRepository;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.persistence.OptimisticLockException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Singleton
public class VacuumCleanerService extends AbstractService<VacuumCleaner, VacuumCleanerRepository>{


    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    @EJB
    private VacuumCleanerRepository vacuumCleanerRepository;

    @EJB
    private ErrorMessageRepository errorMessageRepository;

    @Override
    public VacuumCleanerRepository getRepository() {
        return this.vacuumCleanerRepository;
    }



    public List<VacuumCleaner> searchVacuumCleaners(String name, List<String> statusList, Date dateFrom, Date dateTo, int userId)
    {
        List<VacuumCleaner> lista = vacuumCleanerRepository.getAll();

        return lista.stream()
                .filter(vc->( name==null || vc.getName().toLowerCase(Locale.ROOT).contains(name.toLowerCase())))
                .filter(vc->(statusList==null || statusList.isEmpty() || statusList.contains(vc.getStatus().name())))
                .filter(vc->(dateFrom==null || vc.getCreateDate().after(dateFrom)))
                .filter(vc->(dateTo==null || vc.getCreateDate().before(dateTo)))
                .filter(vc->(vc.getAddedBy().getId()==userId))
                .collect(Collectors.toList());
    }


    public int startVacuumCleaner(int id) {
        VacuumCleaner vc = vacuumCleanerRepository.findByIdVacuumCleaner(id);
        boolean pom=true;
        int pomInt = 0;
        try{
            if (vc.getStatus().equals(Status.OFF) && !vc.getBusy()) {
                vc.setBusy(true);
                vc = this.vacuumCleanerRepository.save(vc);
            } else {
                if(!vc.getStatus().equals(Status.OFF) && vc.getBusy())
                    pomInt=1;
                else if(vc.getBusy())
                    pomInt=2;
                else
                    pomInt=3;
                pom=false;
            }
        }catch (OptimisticLockException e)
        {
            pomInt=2;
            pom=false;
        }

        if(pom)
        {
            VacuumCleaner vc1=vc;
            new Thread(() -> {
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    return;
                }

                vc1.setStatus(Status.ON);
                vc1.setBusy(false);
                try {
                    this.vacuumCleanerRepository.save(vc1);
                }
                catch (OptimisticLockException e)
                {
                    e.printStackTrace();
                }
            }).start();
        }
        return pomInt;
    }

    public int stopVacuumCleaner(int id) {
        VacuumCleaner vc = vacuumCleanerRepository.findByIdVacuumCleaner(id);
        boolean pom=true;
        int pomInt=0;
        try{
            if (vc.getStatus().equals(Status.ON) && !vc.getBusy()) {
                vc.setBusy(true);
                vc = this.vacuumCleanerRepository.save(vc);

            } else {
                if(!vc.getStatus().equals(Status.ON) && vc.getBusy())
                    pomInt=1;
                else if(vc.getBusy())
                    pomInt=2;
                else
                    pomInt=3;
                pom=false;
            }
        }catch (OptimisticLockException e)
        {
            pomInt=2;
            pom=false;
        }

        if(pom)
        {
            AtomicReference<VacuumCleaner> vc1 = new AtomicReference<>(vc);
            new Thread(() -> {
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    return;
                }

                vc1.get().setStatus(Status.OFF);
                vc1.get().setUsed(vc1.get().getUsed()+1);

                if(vc1.get().getUsed()==3)
                {
                    try {
                        Thread.sleep(15000);
                    } catch (InterruptedException e) {
                        return;
                    }

                    vc1.get().setStatus(Status.DISCHARGING);
                    try {
                        vc1.set(this.vacuumCleanerRepository.save(vc1.get()));
                    }
                    catch (OptimisticLockException e)
                    {
                        e.printStackTrace();
                    }

                    try {
                        Thread.sleep(15000);
                    } catch (InterruptedException e) {
                        return;
                    }

                    vc1.get().setStatus(Status.OFF);
                    vc1.get().setBusy(false);
                    vc1.get().setUsed(0);
                    try {
                        this.vacuumCleanerRepository.save(vc1.get());
                    }
                    catch (OptimisticLockException e)
                    {
                        e.printStackTrace();
                    }

                }
                else{
                    vc1.get().setBusy(false);
                    try {
                        this.vacuumCleanerRepository.save(vc1.get());
                    }
                    catch (OptimisticLockException e)
                    {
                        e.printStackTrace();
                    }
                }

            }).start();
        }
        return pomInt;
    }

    public int dischargeVacuumCleaner(int id) {
        VacuumCleaner vc = vacuumCleanerRepository.findByIdVacuumCleaner(id);
        boolean pom=true;
        int pomInt=0;
        try{
            if (vc.getStatus().equals(Status.OFF) && !vc.getBusy()) {
                vc.setBusy(true);
                vc = this.vacuumCleanerRepository.save(vc);
            } else {
                if(!vc.getStatus().equals(Status.OFF) && vc.getBusy())
                    pomInt=1;
                else if(vc.getBusy())
                    pomInt=2;
                else
                    pomInt=3;
                pom=false;
            }
        }catch (OptimisticLockException e)
        {
            pomInt=2;
            pom=false;
        }

        if(pom)
        {
            AtomicReference<VacuumCleaner> vc1= new AtomicReference<>(vc);
            new Thread(() -> {
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    return;
                }

                vc1.get().setStatus(Status.DISCHARGING);
                try {
                    vc1.set(this.vacuumCleanerRepository.save(vc1.get()));
                }
                catch (OptimisticLockException e)
                {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    return;
                }

                vc1.get().setStatus(Status.OFF);
                vc1.get().setBusy(false);
                try {
                    this.vacuumCleanerRepository.save(vc1.get());
                }
                catch (OptimisticLockException e)
                {
                    e.printStackTrace();
                }

            }).start();
        }
        return pomInt;
    }


    public VacuumCleaner myRemoveById(int id)
    {
        return vacuumCleanerRepository.myRemoveById(id);
    }

    private long calculateDelay(Date currentDateTime, Date scheduledDateTime) {
        Instant currentInstant = currentDateTime.toInstant();
        Instant scheduledInstant = scheduledDateTime.toInstant();

        Duration duration = Duration.between(currentInstant, scheduledInstant);
        long secondsDifference = duration.getSeconds();

        return Math.max(0, TimeUnit.SECONDS.toSeconds(secondsDifference));
    }

    public boolean scheduleActivity(Date dateTime,String action,int id)
    {
        Date currentDateTime = new Date();
        long delay = calculateDelay(currentDateTime, dateTime);
        VacuumCleaner vc = vacuumCleanerRepository.findById(id);

        if(action.equals("START"))
            scheduler.schedule(()->scheduleStartOfVacuumCleaner(id,action,dateTime,vc.getAddedBy().getId()), delay, TimeUnit.SECONDS);
        else if(action.equals("STOP"))
            scheduler.schedule(()->scheduleStopOfVacuumCleaner(id,action,dateTime,vc.getAddedBy().getId()),delay,TimeUnit.SECONDS);
        else if(action.equals("DISCHARGE"))
            scheduler.schedule(()->scheduleDischargeOfVacuumCleaner(id,action,dateTime,vc.getAddedBy().getId()),delay,TimeUnit.SECONDS);
        return true;
    }


    public void scheduleStartOfVacuumCleaner(int vacuumCleanerId, String action, Date dateTime,int owner)
    {
        int pom = startVacuumCleaner(vacuumCleanerId);
        if(pom==1)
        {
            errorMessageRepository.add(new ErrorMessage(action,dateTime,vacuumCleanerId,owner,"VacuumCleaner was not in STOPPED mode and it was busy"));
        }
        else if(pom==2)
        {
            errorMessageRepository.add(new ErrorMessage(action,dateTime,vacuumCleanerId,owner,"Vacuum cleaner was busy doing another action"));
        }
        else if(pom==3)
        {
            errorMessageRepository.add(new ErrorMessage(action,dateTime,vacuumCleanerId,owner,"VacuumCleaner was not in STOPPED mode"));
        }
    }

    public void scheduleStopOfVacuumCleaner(int vacuumCleanerId, String action,Date dateTime,int owner)
    {
        int pom = stopVacuumCleaner(vacuumCleanerId);
        if(pom==1)
        {
            errorMessageRepository.add(new ErrorMessage(action,dateTime,vacuumCleanerId,owner,"VacuumCleaner was not in RUNNING mode and it was busy"));
        }
        else if(pom==2)
        {
            errorMessageRepository.add(new ErrorMessage(action,dateTime,vacuumCleanerId,owner,"Vacuum cleaner was busy doing another action"));
        }
        else if(pom==3)
        {
            errorMessageRepository.add(new ErrorMessage(action,dateTime,vacuumCleanerId,owner,"VacuumCleaner was not in RUNNING mode"));
        }
    }

    public void scheduleDischargeOfVacuumCleaner(int vacuumCleanerId, String action, Date dateTime,int owner)
    {
        int pom = dischargeVacuumCleaner(vacuumCleanerId);
        if(pom==1)
        {
            errorMessageRepository.add(new ErrorMessage(action,dateTime,vacuumCleanerId,owner,"VacuumCleaner was not in STOPPED mode and it was busy"));
        }
        else if(pom==2)
        {
            errorMessageRepository.add(new ErrorMessage(action,dateTime,vacuumCleanerId,owner,"Vacuum cleaner was busy doing another action"));
        }
        else if(pom==3)
        {
            errorMessageRepository.add(new ErrorMessage(action,dateTime,vacuumCleanerId,owner,"VacuumCleaner was not in STOPPED mode"));
        }
    }


    public void errorOfVacuumCleaner(int vacuumCleanerId, String action, Date dateTime,int owner,int error)
    {
        if(action.equalsIgnoreCase("START")){
            if(error==1)
            {
                errorMessageRepository.add(new ErrorMessage(action,dateTime,vacuumCleanerId,owner,"VacuumCleaner was not in STOPPED mode and it was busy"));
            }
            else if(error==2)
            {
                errorMessageRepository.add(new ErrorMessage(action,dateTime,vacuumCleanerId,owner,"Vacuum cleaner was busy doing another action"));
            }
            else if(error==3)
            {
                errorMessageRepository.add(new ErrorMessage(action,dateTime,vacuumCleanerId,owner,"VacuumCleaner was not in STOPPED mode"));
            }
        }
        else if(action.equalsIgnoreCase("STOP"))
        {
            if(error==1)
            {
                errorMessageRepository.add(new ErrorMessage(action,dateTime,vacuumCleanerId,owner,"VacuumCleaner was not in RUNNING mode and it was busy"));
            }
            else if(error==2)
            {
                errorMessageRepository.add(new ErrorMessage(action,dateTime,vacuumCleanerId,owner,"Vacuum cleaner was busy doing another action"));
            }
            else if(error==3)
            {
                errorMessageRepository.add(new ErrorMessage(action,dateTime,vacuumCleanerId,owner,"VacuumCleaner was not in RUNNING mode"));
            }
        }
        else
        {
            if(error==1)
            {
                errorMessageRepository.add(new ErrorMessage(action,dateTime,vacuumCleanerId,owner,"VacuumCleaner was not in STOPPED mode and it was busy"));
            }
            else if(error==2)
            {
                errorMessageRepository.add(new ErrorMessage(action,dateTime,vacuumCleanerId,owner,"Vacuum cleaner was busy doing another action"));
            }
            else if(error==3)
            {
                errorMessageRepository.add(new ErrorMessage(action,dateTime,vacuumCleanerId,owner,"VacuumCleaner was not in STOPPED mode"));
            }
        }
    }







    public List<VacuumCleaner> getAllVacuumCleanersForUser(int userId) {
        List<VacuumCleaner> lista =  vacuumCleanerRepository.getAll();
        return lista.stream()
                .filter(vc->( vc.getAddedBy().getId()==userId))
                .collect(Collectors.toList());
    }
}




























//        VacuumCleaner vc = vacuumCleanerRepository.findByIdVacuumCleaner(id);
//
//        if (vc == null) {
//            return false;
//        }
//
//        boolean pom=true;
//        synchronized (vc) {
//            if (vc.getStatus().equals(Status.OFF) && !vc.getBusy()) {
//                vc.setBusy(true);
//                this.vacuumCleanerRepository.save(vc);
//
//            } else {
//                pom=false;
//            }
//        }
//
//        if(pom)
//        {
//            new Thread(() -> {
//                try {
//                    Thread.sleep(15000);
//                } catch (InterruptedException e) {
//                    return;
//                }
//
//                synchronized (vc) {
//                    vc.setStatus(Status.ON);
//                    vc.setBusy(false);
//                    this.vacuumCleanerRepository.save(vc);
//                }
//            }).start();
//        }
//
//        return pom;
