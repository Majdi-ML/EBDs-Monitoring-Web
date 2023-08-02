<?php

namespace App\Form;

use App\Entity\LogFilesPatterns;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class LogFilesPatternsType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nRef')
            ->add('ref')
            ->add('etat')
            ->add('signification')
            ->add('identification')
            ->add('criticite')
            ->add('messageAlarme')
            ->add('instructions')
            ->add('performAction')
            ->add('acquittement')
            ->add('complementsInformations')
            ->add('refService')
            ->add('objet')
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => LogFilesPatterns::class,
        ]);
    }
}
