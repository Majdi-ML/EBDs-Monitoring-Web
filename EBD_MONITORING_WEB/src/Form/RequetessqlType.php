<?php

namespace App\Form;

use App\Entity\Requetessql;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class RequetessqlType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('ref')
            ->add('etat')
            ->add('refComposant')
            ->add('rgSgSiCluster')
            ->add('requeteSql')
            ->add('usernameDbName')
            ->add('resultatAttenduDeLaRequete')
            ->add('performAction')
            ->add('criticite')
            ->add('messageAlarme')
            ->add('instructions')
            ->add('intervalleDePolling')
            ->add('refService')
            ->add('objet')
            ->add('monitoredBy')
            ->add('nomTemplate')
            ->add('support')
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Requetessql::class,
        ]);
    }
}
