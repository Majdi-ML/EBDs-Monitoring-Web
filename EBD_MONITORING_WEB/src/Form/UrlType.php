<?php

namespace App\Form;

use App\Entity\Url;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class UrlType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('ref')
            ->add('etat')
            ->add('refComposant')
            ->add('rgSgSiCluster')
            ->add('url')
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
            'data_class' => Url::class,
        ]);
    }
}
