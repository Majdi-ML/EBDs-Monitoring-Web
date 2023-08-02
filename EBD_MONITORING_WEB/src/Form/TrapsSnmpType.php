<?php

namespace App\Form;

use App\Entity\TrapsSnmp;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class TrapsSnmpType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('ref')
            ->add('etat')
            ->add('refComposant')
            ->add('signification')
            ->add('versionSnmp')
            ->add('oid')
            ->add('specificTrap')
            ->add('variableBinding')
            ->add('criticite')
            ->add('messageAlarme')
            ->add('instructions')
            ->add('acquittement')
            ->add('mibAssocie')
            ->add('objet')
            ->add('compelementInformation')
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => TrapsSnmp::class,
        ]);
    }
}
