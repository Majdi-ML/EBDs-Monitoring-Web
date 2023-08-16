<?php

namespace App\Form;

use App\Entity\LogFiles;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class LogFilesType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('ref')
            ->add('etat')
            ->add('refComposant')
            ->add('rgSgSiCluster')
            ->add('logfile')
            ->add('localisation')
            ->add('description')
            ->add('formatLogfile')
            ->add('separateur')
            ->add('intervalleDePolling')
            ->add('monitoredBy')
            ->add('fourniEnAnnexe')
            ->add('refService')
            ->add('nomTemplate')
            ->add('logConditions')
            ->add('support')
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => LogFiles::class,
        ]);
    }
}
